package com.kodama.sleeptime.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kodama.sleeptime.R
import com.kodama.sleeptime.adapter.WakeUpTimeAdapter

class SleepNowFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sleep_now, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rview_sleep_now)

        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
        recyclerView.adapter = WakeUpTimeAdapter(requireContext())

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = SleepNowFragment()
    }
}