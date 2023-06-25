package com.semihozmen.lkelerkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semihozmen.lkelerkotlin.model.CountryModel

class DetailsViewModel:ViewModel() {

    val countryLiveData = MutableLiveData<CountryModel>()

    fun getDataFromRoom(){

    }

}