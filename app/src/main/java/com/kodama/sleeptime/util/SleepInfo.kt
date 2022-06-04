package com.kodama.sleeptime.util

import java.time.LocalTime
import java.time.temporal.ChronoUnit
import kotlin.math.floor
import kotlin.math.min

class SleepInfo {
    private val cycleDuration = 90 // one cycle duration in minutes
    private val napDuration = 30

    /**
     * Returns the time when to wake up and the length of sleep, from now
     *
     * @param [cycleCount] Number of sleep cycle
     * @param [minutesOffset] Additional time, e.g time to fall asleep
     * @param [startTime] The time you plan to go to bed
     * @return [Pair] including [Pair.first] - wake up time and [Pair.second] - sleep length
     */
    fun getSleepTimes(cycleCount: Int, minutesOffset: Int, startTime: LocalTime) : Pair<LocalTime, LocalTime>{
        val sleepLengthMinutes = when(cycleCount){
            0 -> napDuration
            in 1..Int.MAX_VALUE -> cycleDuration * cycleCount + minutesOffset
            else -> napDuration
        }

        val sleepLength = LocalTime.of(floor(sleepLengthMinutes / 60f).toInt(), sleepLengthMinutes % 60)
        var wakeUpAt = startTime.plusHours(sleepLengthMinutes / 60L)
        wakeUpAt = wakeUpAt.plusMinutes(sleepLengthMinutes % 60L).truncatedTo(ChronoUnit.MINUTES)

        return Pair(wakeUpAt, sleepLength)
    }
}