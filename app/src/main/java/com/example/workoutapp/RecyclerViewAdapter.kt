package com.example.workoutapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val dailyLogs: MutableList<DailyLog>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val dateTextView: TextView = itemView.findViewById(R.id.title)
        val descriptionView: TextView = itemView.findViewById(R.id.description)
        val deleteButton: Button = itemView.findViewById(R.id.del_btn)
    }
    override fun getItemCount(): Int { return dailyLogs.size }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        //add the custom layout to the home_fragment layout
        val contentView = inflater.inflate(R.layout.home_page_list_item, parent, false)
        return ViewHolder(contentView)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        val dailyLog: DailyLog = dailyLogs[position]

        val titleTextView = holder.dateTextView
        titleTextView.text = dailyLog.title

        val descriptionTextView = holder.descriptionView
        descriptionTextView.text = dailyLog.description
        val button = holder.deleteButton
        button.setOnClickListener{
            dailyLogs.removeAt(position)
            this.notifyItemRangeRemoved(position, dailyLogs.size)
        }
    }
}