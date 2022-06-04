package com.kodama.sleeptime.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.provider.AlarmClock
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kodama.sleeptime.R
import com.kodama.sleeptime.util.SleepInfo
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class WakeUpTimeAdapter(private val context: Context, startTime: LocalTime = LocalTime.now()) : RecyclerView.Adapter<WakeUpTimeAdapter.ViewHolder>() {
    private var wakeUpTimes = arrayListOf<LocalTime>()
    private var sleepLength = arrayListOf<LocalTime>()
    private var cycles = arrayListOf<Int>()

    private val totalCycles = 6
    private val minutesOffset = 15

    init{
        val sleepInfo = SleepInfo()
        for(i in 0..totalCycles){
            val sleepTimeResult = sleepInfo.getSleepTimes(i, minutesOffset, startTime)

            wakeUpTimes.add(sleepTimeResult.first)
            sleepLength.add(sleepTimeResult.second)
            cycles.add(i)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.card_wake_up_time, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.wakeUpTimeText.text =
            if(DateFormat.is24HourFormat(context))
                wakeUpTimes[position].toString()
            else
                wakeUpTimes[position].format(DateTimeFormatter.ofPattern("hh:mm a")).toString()

        if(sleepLength[position].hour == 0){
            holder.sleepLengthText.text = context.getString(R.string.card_wake_up_time_nap, sleepLength[position].minute.toString().padStart(2,'0'))
        }else{
            holder.sleepLengthText.text = context.getString(R.string.card_wake_up_time,
                sleepLength[position].hour.toString(),
                sleepLength[position].minute.toString().padStart(2,'0'))
        }

        holder.cyclesCountText.text =
            if(cycles[position] == 0)
                context.getString(R.string.nap)
            else
                context.resources.getQuantityString(R.plurals.card_wake_up_cycles, cycles[position], cycles[position])

        holder.setAlarm.setOnClickListener {
            val alarmIntent = Intent(AlarmClock.ACTION_SET_ALARM)

            alarmIntent.putExtra(AlarmClock.EXTRA_HOUR, wakeUpTimes[position].hour)
            alarmIntent.putExtra(AlarmClock.EXTRA_MINUTES,  wakeUpTimes[position].minute)
            alarmIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

            context.startActivity(alarmIntent)
        }
    }

    override fun getItemCount(): Int = wakeUpTimes.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var wakeUpTimeText: TextView = itemView.findViewById(R.id.wake_up_time_header)
        var cyclesCountText: TextView = itemView.findViewById(R.id.wake_up_cycles_text)
        var sleepLengthText: TextView = itemView.findViewById(R.id.wake_up_sleep_time_text)
        var setAlarm: ImageButton = itemView.findViewById(R.id.wake_up_set_alarm)
    }
}