package com.semihozmen.lkelerkotlin.service

import com.semihozmen.lkelerkotlin.model.CountryModel
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface CountryAPI {

    // https://raw.githubusercontent.com/
// atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun gatAll():Single<List<CountryModel>>

}