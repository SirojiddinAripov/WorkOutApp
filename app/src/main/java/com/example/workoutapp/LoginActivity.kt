package com.example.workoutapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.io.File
import java.util.Random

class LoginActivity : AppCompatActivity() {
    private lateinit var username : EditText
    private lateinit var startButton : Button

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this@LoginActivity, R.color.white)
        username = findViewById(R.id.username)
        startButton = findViewById(R.id.start)

        val random = Random()
        val randNum = random.nextDouble()

        if(randNum >0.50){
            findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.login).setBackgroundResource(R.drawable.dumb_bell_rack)
        }else{
            findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.login).setBackgroundResource(R.drawable.dumb_bell_rack_2)
        }

        startButton.setOnClickListener {
            if (Environment.isExternalStorageManager()) {
                if(username.text.toString().isNotEmpty()){
                    val folderName = username.text.toString()
                    val dir = File(Environment.getExternalStorageDirectory(), "workout-tracker/${folderName}")

                    if(!dir.exists()){
                        if(dir.mkdirs()){
                            Toast.makeText(this, dir.absolutePath, Toast.LENGTH_SHORT).show()
                        }
                    }
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("Folder-Location", dir.absolutePath)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this, "Please enter your username.", Toast.LENGTH_SHORT).show()
                }
            } else {
                //request for the permission
                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                val uri: Uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            }
        }

    }
}