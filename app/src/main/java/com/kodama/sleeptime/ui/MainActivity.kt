package com.kodama.sleeptime.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.kodama.sleeptime.R
import com.kodama.sleeptime.adapter.FragmentViewPager2Adapter
import com.kodama.sleeptime.ui.fragment.SleepNowFragment
import io.ak1.BubbleTabBar

class MainActivity : AppCompatActivity() {
    // list of viewPager2 fragments
    private val fragmentList = arrayListOf<Fragment>(
        SleepNowFragment.newInstance())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bubbleTabBar = findViewById<BubbleTabBar>(R.id.bubbleTabBar)
        val viewPager2 = findViewById<ViewPager2>(R.id.viewPager2Container)

        viewPager2.adapter = FragmentViewPager2Adapter(this, fragmentList)
    }
}