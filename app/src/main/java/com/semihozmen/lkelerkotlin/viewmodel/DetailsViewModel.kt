package com.semihozmen.lkelerkotlin.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semihozmen.lkelerkotlin.model.CountryModel
import com.semihozmen.lkelerkotlin.service.CountryDatabase
import kotlinx.coroutines.launch

class DetailsViewModel(application: Application):BaseViewModel(application) {

    val countryLiveData = MutableLiveData<CountryModel>()

    fun getDataFromRoom(id:Int){

        launch {
            countryLiveData.value = CountryDatabase(getApplication()).countryDao().getCountryById(id)
        }

    }

}