package com.example.workoutapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.workoutapp.fragments.FragmentAdapter
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intent.getStringExtra("Folder-Location")

        supportActionBar?.title = "Home Page"
        supportActionBar?.setBackgroundDrawable(ContextCompat.getDrawable(this, R.color.colorPrimary))

        viewPager = findViewById(R.id.viewPager2)
        tabLayout = findViewById(R.id.tabLayout)

        tabLayout.addTab(tabLayout.newTab().setText("Home"))
        tabLayout.getTabAt(0)?.icon = ContextCompat.getDrawable(this, R.drawable.home_tab_icon)
        tabLayout.addTab(tabLayout.newTab().setText("Workout"))
        tabLayout.getTabAt(1)?.icon = ContextCompat.getDrawable(this, R.drawable.dumbbell_tab_icon)
        tabLayout.addTab(tabLayout.newTab().setText("Diet"))
        tabLayout.getTabAt(2)?.icon = ContextCompat.getDrawable(this, R.drawable.diet_tab_icon)


        viewPager.adapter = FragmentAdapter(supportFragmentManager, lifecycle)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
                when(tab.position){
                    0 -> supportActionBar?.title = "Home Page"
                    1 -> supportActionBar?.title = "Workout List"
                    2 -> supportActionBar?.title = "Diet Menu"
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }

}
