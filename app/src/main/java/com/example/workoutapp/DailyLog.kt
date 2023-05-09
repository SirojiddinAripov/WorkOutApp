package com.example.workoutapp

import android.graphics.drawable.Drawable

class DailyLog(
    val date: String,
    val time: String,
    val dayRating: Int,
    val picture: Drawable?,
    val title: String,
    val description: String
)