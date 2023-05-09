package com.example.workoutapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeCardDetail: AppCompatActivity() {
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_card_detail)
        supportActionBar?.hide()

        textView = findViewById(R.id.home_card_title)
        textView.text = intent.getStringExtra("title").toString()
    }
}