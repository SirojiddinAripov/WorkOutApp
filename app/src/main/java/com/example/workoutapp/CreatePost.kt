package com.example.workoutapp

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import java.time.LocalTime
import java.util.Calendar

class CreatePost: AppCompatActivity() {
    private lateinit var date: TextView
    private lateinit var imageButton: ImageButton
    private lateinit var title: EditText
    private lateinit var description: EditText
    private lateinit var rating: EditText
    private lateinit var imageDrawable : Uri
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_post_layout)
        supportActionBar?.hide()

        date = findViewById(R.id.date)
        date.text = getCurrentDate()

        rating = findViewById(R.id.edit_rating)
        rating.doOnTextChanged { text, start, before, count ->
            if(!text?.isEmpty()!!) {
                if (text.toString().toInt() < 0 || text.toString().toInt() > 10) {
                    rating.setText(text.toString().split("")[1])
                }
            }
        }

        imageButton = findViewById(R.id.edit_image_button)
        imageButton.setOnClickListener {
            selectImageFromGallery()
        }

        title = findViewById(R.id.edit_title)
        description = findViewById(R.id.edit_description)

        findViewById<Button>(R.id.cancel_btn).setOnClickListener{
            this.finish()
        }
        findViewById<Button>(R.id.create_btn).setOnClickListener {
            dailyLogsList.add(
                DailyLog(
                    date.text.toString(),
                    getCurrentTime(),
                    rating.text.toString().toInt(),
                    imageDrawable,
                    title.text.toString(),
                    description.text.toString()
            ))
            Log.v("List len:", dailyLogsList.size.toString())
            HomePageRecyclerViewAdapter(dailyLogsList)
            this.finish()
        }
    }
    private fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val day: String = String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH))
        val month: String = String.format("%02d", calendar.get(Calendar.MONTH)+1)
        val year = calendar.get(Calendar.YEAR)
        return "$day/$month/$year"
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun getCurrentTime(): String {
        val time = LocalTime.now()
        val hour = time.hour
        val minutes = time.minute
        return "$hour:$minutes"
    }

    private val PICK_IMAGE_REQUEST = 2
    private fun selectImageFromGallery(){
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null){
            val selectedImageURI: Uri? = data.data
            if (selectedImageURI != null) {
                imageDrawable = selectedImageURI
            }
            imageButton.setImageURI(selectedImageURI)
        }
    }

}