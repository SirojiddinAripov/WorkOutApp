package com.example.workoutapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import java.util.Calendar
import java.util.Date

class CreatePost: AppCompatActivity() {
    private lateinit var date: TextView
    private lateinit var imageButton: ImageButton
    private lateinit var title: EditText
    private lateinit var description: EditText
    private lateinit var rating: EditText
    private lateinit var imageDrawable : Drawable
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


    }
    private fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val day: String = String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH))
        val month: String = String.format("%02d", calendar.get(Calendar.MONTH)+1)
        val year = calendar.get(Calendar.YEAR)
        return "$day/$month/$year"
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
            val inputStream = selectedImageURI?.let { contentResolver.openInputStream(it) }
            val bitmap = BitmapFactory.decodeStream(inputStream)
            imageDrawable = BitmapDrawable(resources, bitmap)
            imageButton.setImageDrawable(imageDrawable)
        }
    }
}