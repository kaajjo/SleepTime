package com.kodama.sleeptime.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.kodama.sleeptime.R
import com.kodama.sleeptime.adapter.WakeUpTimeAdapter
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

        val headerText = root.findViewById<TextView>(R.id.at_header_text)
        val holderText = root.findViewById<TextView>(R.id.at_holder_text)

        val fab = root.findViewById<FloatingActionButton>(R.id.at_fab)
        fab.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
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