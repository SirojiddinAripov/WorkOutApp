package com.example.workoutapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.DailyLog
import com.example.workoutapp.R
import com.example.workoutapp.RecyclerViewAdapter

class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView : RecyclerView = view.findViewById(R.id.daily_logs)
        val dailyLogsList = mutableListOf<DailyLog>()

        for(i in 0 .. 100){
            dailyLogsList.add(DailyLog("Title: $i", "Description: $i"))
        }
        Toast.makeText(activity, dailyLogsList[10].description, Toast.LENGTH_SHORT).show()
        val adapter = RecyclerViewAdapter(dailyLogsList)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }
}