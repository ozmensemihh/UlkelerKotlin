package com.semihozmen.lkelerkotlin.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.semihozmen.lkelerkotlin.model.CountryModel


@Database(entities = arrayOf(CountryModel::class), version = 1)
abstract class CountryDatabase : RoomDatabase () {

    abstract fun countryDao():CountryDao

    companion object{
        @Volatile private var instance : CountryDatabase? = null


        operator fun invoke(context: Context) = instance ?: synchronized(Any()){
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }


        private fun makeDatabase(context:Context)= Room.databaseBuilder(
            context.applicationContext,CountryDatabase::class.java,"Countries"
        ).build()

    }

}