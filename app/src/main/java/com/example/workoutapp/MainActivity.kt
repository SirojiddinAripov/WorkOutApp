package com.example.workoutapp

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.workoutapp.fragments.FragmentAdapter
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val secondaryColorHex: String = String.format("#%06X", 0xFFFFFF and ContextCompat.getColor(this@MainActivity, R.color.colorSecondary))
        Log.v("54564:","<font color='#$secondaryColorHex'>Home Page</font>")
        intent.getStringExtra("Folder-Location")
        supportActionBar?.title = Html.fromHtml("<bold><font color='$secondaryColorHex'>Home Page</font></bold>")
        supportActionBar?.setBackgroundDrawable(ContextCompat.getDrawable(this@MainActivity, R.color.white))

        navView = findViewById(R.id.nav_view)
        drawerLayout = findViewById(R.id.drawer_layout)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.profile -> Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                R.id.settings -> Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
            }
            true
        }


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
                viewPager.currentItem = tab.position
                tab.icon?.setTint(Color.WHITE)
                when(tab.position){
                    0 -> { supportActionBar?.title = Html.fromHtml("<bold><font color='$secondaryColorHex'>Home Page</font></bold>")}
                    1 -> { supportActionBar?.title = Html.fromHtml("<bold><font color='$secondaryColorHex'>Workout List</font></bold>")}
                    2 -> { supportActionBar?.title = Html.fromHtml("<bold><font color='$secondaryColorHex'>Diet Menu</font></bold>")}
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
