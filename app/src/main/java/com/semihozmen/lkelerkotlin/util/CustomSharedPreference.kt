package com.semihozmen.lkelerkotlin.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class CustomSharedPreference {


    companion object {

        private var sharedPreferences:SharedPreferences? = null

        @Volatile private var instance : CustomSharedPreference? = null

        operator fun invoke (context: Context) :CustomSharedPreference = instance?: synchronized(Any()){
            instance ?: makeSharedPreference(context).also {
                instance = it
            }
        }

        private fun makeSharedPreference(context: Context):CustomSharedPreference{
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return CustomSharedPreference()
        }


    }

    fun saveTime(time:Long){
        sharedPreferences?.edit(commit = true){
            putLong("time",time)
        }
    }

    fun getTime() = sharedPreferences?.getLong("time",0)

}