package com.example.workoutapp

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HomePageRecyclerViewAdapter(private val dailyLogs: MutableList<DailyLog>) : RecyclerView.Adapter<HomePageRecyclerViewAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleTextView: TextView = itemView.findViewById(R.id.title)
        val imageButton: ImageButton = itemView.findViewById(R.id.image_button)
        val dateView: TextView = itemView.findViewById(R.id.date)
        val ratingView: TextView = itemView.findViewById(R.id.rating)
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

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HomePageRecyclerViewAdapter.ViewHolder, position: Int) {
        val dailyLog: DailyLog = dailyLogs[position]

        val titleTextView = holder.titleTextView
        titleTextView.text = dailyLog.title

        val dateTextView = holder.dateView
        dateTextView.text = dailyLog.date

        val imageButtonView = holder.imageButton
        imageButtonView.setImageURI(dailyLog.imageSrcUri)
        imageButtonView.setOnClickListener{
            val context = holder.itemView.context
            val intent = Intent(context , HomeCardDetail::class.java)
            intent.putExtra("object", dailyLog)
            context.startActivity(intent)
        }

        holder.ratingView.text = "${dailyLog.dayRating}/10"

        val button = holder.deleteButton
        button.setOnClickListener{
            dailyLogs.removeAt(position)
            this.notifyItemRangeRemoved(position, dailyLogs.size)
        }
    }
}