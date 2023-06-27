package com.semihozmen.lkelerkotlin.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.semihozmen.lkelerkotlin.model.CountryModel

@Dao
interface CountryDao {

    @Insert
    suspend fun insertAll(vararg countries:CountryModel):List<Long>

    @Query("Select * from CountryModel")
    suspend fun getAll():List<CountryModel>

    @Query("Select * from CountryModel where id = :id")
    suspend fun getCountryById(id:Int):CountryModel

    @Query ("Delete from CountryModel")
    suspend fun deleteAll()

}