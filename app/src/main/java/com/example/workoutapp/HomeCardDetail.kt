package com.example.workoutapp

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeCardDetail: AppCompatActivity() {
    private lateinit var titleView: TextView
    private lateinit var descriptionView: TextView
    private lateinit var ratingView: TextView
    private lateinit var imageButton: ImageButton
    private lateinit var dateView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_card_detail)
        supportActionBar?.hide()

        val dailyLog = intent.getParcelableExtra<DailyLog>("object")

        titleView = findViewById(R.id.title)
        titleView.text = dailyLog?.title

        descriptionView = findViewById(R.id.description)
        descriptionView.text = dailyLog?.description

        ratingView = findViewById(R.id.rating)
        ratingView.text = "${dailyLog?.dayRating}/10"

        imageButton = findViewById(R.id.image_button)
        dailyLog?.imageSrcId?.let { imageButton.setImageResource(it) }

        dateView = findViewById(R.id.date)
        dateView.text = dailyLog?.date
    }
}