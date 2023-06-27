package com.semihozmen.lkelerkotlin.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semihozmen.lkelerkotlin.model.CountryModel
import com.semihozmen.lkelerkotlin.service.CountryAPI
import com.semihozmen.lkelerkotlin.service.CountryDatabase
import com.semihozmen.lkelerkotlin.service.CountryService
import com.semihozmen.lkelerkotlin.util.CustomSharedPreference
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch


class FeedViewModel(application: Application):BaseViewModel(application) {

    private val countryService = CountryService()
    private val disposable = CompositeDisposable()
    private val sharedPreferences = CustomSharedPreference(getApplication())
    private val refreshTime = 10*60*1000*1000*1000L

    val countries = MutableLiveData<List<CountryModel>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()



    fun refreshData(){

        val updatedTime = sharedPreferences.getTime()
        if(updatedTime!= null && updatedTime != 0L && System.nanoTime() < refreshTime ){
            getDataFromRoom()
        }else{
            getDataFromAPI()
        }


    }

    private fun getDataFromRoom() {
        launch {
            val countries = CountryDatabase(getApplication()).countryDao().getAll()
            showCountries(countries)
        }
    }

    private fun getDataFromAPI(){
        countryLoading.value = true
        disposable.add(
            countryService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<List<CountryModel>>(){
                    override fun onSuccess(t: List<CountryModel>) {
                        storeInRoom(t)
                    }

                    override fun onError(e: Throwable) {
                        countryError.value = true
                        countryLoading.value = false
                    }

                })
        )

    }

    private fun showCountries(t: List<CountryModel>){
        countryError.value = false
        countryLoading.value = false
        countries.value = t
    }

    private fun storeInRoom (t: List<CountryModel>){
        launch {
            val db = CountryDatabase.invoke(getApplication()).countryDao()
            db.deleteAll()
            val listLong = db.insertAll(*t.toTypedArray())
            var i =0
            while (i<listLong.size){
                t[i].id = listLong[i].toInt()
                i++
            }

            showCountries(t)
        }

        sharedPreferences.saveTime(System.nanoTime())
    }


}