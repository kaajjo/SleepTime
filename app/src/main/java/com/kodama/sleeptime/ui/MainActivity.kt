package com.kodama.sleeptime.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import androidx.viewpager2.widget.ViewPager2
import com.kodama.sleeptime.R
import com.kodama.sleeptime.adapter.FragmentViewPager2Adapter
import com.kodama.sleeptime.ui.fragment.SleepAtFragment
import com.kodama.sleeptime.ui.fragment.SleepNowFragment
import com.kodama.sleeptime.util.Constants
import io.ak1.BubbleTabBar

class MainActivity : AppCompatActivity() {
    // list of viewPager2 fragments
    private val fragmentList = arrayListOf<Fragment>(
        SleepNowFragment.newInstance(),
        SleepAtFragment.newInstance())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        when(PreferenceManager.getDefaultSharedPreferences(this).getBoolean(Constants.NIGTH_MODE_KEY, true)) {
            true -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            false -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        val bubbleTabBar = findViewById<BubbleTabBar>(R.id.bubbleTabBar)
        val viewPager2 = findViewById<ViewPager2>(R.id.viewPager2Container)

        viewPager2.adapter = FragmentViewPager2Adapter(this, fragmentList)

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bubbleTabBar.setSelected(position)
            }
        })

        bubbleTabBar.addBubbleListener {
            viewPager2.currentItem = when (it) {
                R.id.now -> 0
                R.id.at_time -> 1
                else -> 0
            }
        }
    }
}