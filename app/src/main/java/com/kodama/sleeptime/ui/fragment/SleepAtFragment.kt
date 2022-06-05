package com.kodama.sleeptime.ui.fragment

import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.timepicker.MaterialTimePicker
import com.kodama.sleeptime.R
import com.kodama.sleeptime.adapter.WakeUpTimeAdapter
import com.kodama.sleeptime.util.ThemeUtils
import android.text.format.DateFormat
import com.google.android.material.timepicker.TimeFormat.CLOCK_12H
import com.google.android.material.timepicker.TimeFormat.CLOCK_24H
import java.time.LocalTime

class SleepAtFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_sleep_at, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.at_rview)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val toolbar = root.findViewById<Toolbar>(R.id.sleep_at_toolbar)
        toolbar.inflateMenu(R.menu.main_menu)

        val themeUtils = ThemeUtils()
        toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.menu_main_change_theme -> themeUtils.changeDayNightMode(requireContext())
            }
            true
        }

        val headerText = root.findViewById<TextView>(R.id.at_header_text)
        val holderText = root.findViewById<TextView>(R.id.at_holder_text)

        val fab = root.findViewById<FloatingActionButton>(R.id.at_fab)
        fab.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(if(is24HourFormat(requireContext())) CLOCK_24H else CLOCK_12H)
                .setHour(LocalTime.now().hour)
                .setMinute(LocalTime.now().minute)
                .build()

            timePicker.addOnPositiveButtonClickListener{
                val selectedTime = LocalTime.of(timePicker.hour, timePicker.minute)

                headerText.text = requireContext().getString(R.string.sleep_at_header, selectedTime.toString())
                recyclerView.adapter = WakeUpTimeAdapter(requireContext(), selectedTime)

                holderText.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }

            timePicker.show(requireActivity().supportFragmentManager, "time_picker")
        }
        return root
    }

    companion object {
        @JvmStatic
        fun newInstance() = SleepAtFragment()
    }
}