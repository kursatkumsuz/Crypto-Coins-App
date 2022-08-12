package com.kursatdrhistoryapp.cryptocoins.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit


class CustomSharedPreferences {
    companion object {
        private var sharedPreferences : SharedPreferences? = null
        @Volatile private var instance : CustomSharedPreferences? = null

        private var lock = Any()

        operator fun invoke(context : Context) : CustomSharedPreferences = instance ?: synchronized(lock) {
            instance ?: makeSharedPreferences(context).also {
                instance = it
            }
        }

        private fun makeSharedPreferences(context : Context) : CustomSharedPreferences {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return CustomSharedPreferences()
        }
    }

    fun saveTime(time : Long) {
        sharedPreferences?.edit(commit = true){
            putLong("time" , time)
        }
    }

    fun getTime() = sharedPreferences?.getLong("time" , 0)
}