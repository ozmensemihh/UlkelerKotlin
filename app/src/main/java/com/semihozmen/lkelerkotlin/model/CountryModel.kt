package com.semihozmen.lkelerkotlin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity
data class CountryModel
    @Inject // Injectable olduğunu bildiriyoruz.
    constructor
    (
    @ColumnInfo(name = "name")
    val name:String?,

    @ColumnInfo(name = "region")
    val region:String?,

    @ColumnInfo(name = "capital")
    val capital:String?,

    @ColumnInfo(name = "currency")
    val currency:String?,

    @ColumnInfo(name = "language")
    val language:String?,

    @ColumnInfo(name = "flag")
    val flag:String?
){
    @PrimaryKey(autoGenerate = true)
    var id :Int = 0
}