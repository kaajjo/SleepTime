package com.kodama.sleeptime.util

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager

class ThemeUtils {
    fun changeDayNightMode(context: Context){
        val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()

        when (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor.putBoolean(Constants.NIGTH_MODE_KEY, true)
                editor.apply()
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor.putBoolean(Constants.NIGTH_MODE_KEY, false)
                editor.apply()
            }
        }
    }
}