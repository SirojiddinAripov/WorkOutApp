package com.example.workoutapp

import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.util.Log
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

        val secondaryColorHex: String = String.format("#%06X", 0xFFFFFF and ContextCompat.getColor(this@MainActivity, R.color.colorSecondary))
        Log.v("54564:","<font color='#$secondaryColorHex'>Home Page</font>")
        intent.getStringExtra("Folder-Location")
        supportActionBar?.title = Html.fromHtml("<font color='$secondaryColorHex'>Home Page</font>")
        supportActionBar?.setBackgroundDrawable(ContextCompat.getDrawable(this@MainActivity, R.color.white))

        viewPager = findViewById(R.id.viewPager2)
        tabLayout = findViewById(R.id.tabLayout)

        tabLayout.addTab(tabLayout.newTab().setText("Home"))
        tabLayout.getTabAt(0)?.icon = ContextCompat.getDrawable(this, R.drawable.home_tab_icon)
        tabLayout.getTabAt(0)?.icon?.setTint(ContextCompat.getColor(this@MainActivity, R.color.white))

        tabLayout.addTab(tabLayout.newTab().setText("Workout"))
        tabLayout.getTabAt(1)?.icon = ContextCompat.getDrawable(this, R.drawable.dumbbell_tab_icon)
        tabLayout.getTabAt(1)?.icon?.setTint(ContextCompat.getColor(this@MainActivity, R.color.colorSecondary))

        tabLayout.addTab(tabLayout.newTab().setText("Diet"))
        tabLayout.getTabAt(2)?.icon = ContextCompat.getDrawable(this, R.drawable.diet_tab_icon)
        tabLayout.getTabAt(2)?.icon?.setTint(ContextCompat.getColor(this@MainActivity, R.color.colorSecondary))

        viewPager.adapter = FragmentAdapter(supportFragmentManager, lifecycle)


        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tab.icon?.setTint(Color.WHITE)
                when(tab.position){
                    0 -> { supportActionBar?.title = Html.fromHtml("<font color='$secondaryColorHex'>Home Page</font>")}
                    1 -> { supportActionBar?.title = Html.fromHtml("<font color='$secondaryColorHex'>Workout List</font>")}
                    2 -> { supportActionBar?.title = Html.fromHtml("<font color='$secondaryColorHex'>Diet Menu</font>")}
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.icon?.setTint(ContextCompat.getColor(this@MainActivity, R.color.colorSecondary))
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
