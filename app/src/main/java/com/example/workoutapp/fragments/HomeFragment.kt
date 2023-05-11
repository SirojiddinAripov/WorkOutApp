package com.example.workoutapp.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.CreatePost
import com.example.workoutapp.DailyLog
import com.example.workoutapp.HomeCardDetail
import com.example.workoutapp.R
import com.example.workoutapp.HomePageRecyclerViewAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalTime
import java.util.Calendar

class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView : RecyclerView = view.findViewById(R.id.daily_logs)
        val dailyLogsList = mutableListOf<DailyLog>()

        for(i in 0 .. 100){
            dailyLogsList.add(
                DailyLog(
                    getCurrentDate(),
                    getCurrentTime(),
                    (1..10).random(),
                    R.drawable.work_out_man,
                    "Title: $i",
                    "Description: $i"))
        }
        val adapter = HomePageRecyclerViewAdapter(dailyLogsList)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        val createPostButton : FloatingActionButton = view.findViewById(R.id.create_post)
        createPostButton.setOnClickListener {
            val intent = Intent(activity, CreatePost::class.java)
            startActivity(intent)
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
}