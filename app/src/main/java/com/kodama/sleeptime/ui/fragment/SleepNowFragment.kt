package com.kodama.sleeptime.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kodama.sleeptime.R
import com.kodama.sleeptime.adapter.WakeUpTimeAdapter
import com.kodama.sleeptime.util.ThemeUtils

class SleepNowFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_sleep_now, container, false)

        val toolbar = root.findViewById<Toolbar>(R.id.sleep_now_toolbar)
        toolbar.inflateMenu(R.menu.main_menu)

        val themeUtils = ThemeUtils()
        toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.menu_main_change_theme -> themeUtils.changeDayNightMode(requireContext())
            }
            true
        }

        val recyclerView = root.findViewById<RecyclerView>(R.id.rview_sleep_now)

        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
        recyclerView.adapter = WakeUpTimeAdapter(requireContext())

        return root
    }

    companion object {
        @JvmStatic
        fun newInstance() = SleepNowFragment()
    }
}