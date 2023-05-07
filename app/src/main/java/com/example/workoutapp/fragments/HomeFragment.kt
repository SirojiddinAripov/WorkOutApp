package com.example.workoutapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.workoutapp.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val myBtn: Button = view.findViewById(R.id.change_txt_btn)
        myBtn.setOnClickListener {
            val myText : TextView = view.findViewById(R.id.textView)
            if(!myText.text.equals("New Text")){
                myText.text = "New Text"
            }else{
                myText.text = "Old Text"
            }
        }
    }
}