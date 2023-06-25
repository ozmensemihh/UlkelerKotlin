package com.semihozmen.lkelerkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.semihozmen.lkelerkotlin.model.CountryModel
import com.semihozmen.lkelerkotlin.service.CountryAPI
import com.semihozmen.lkelerkotlin.service.CountryService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class FeedViewModel:ViewModel() {

    private val countryService = CountryService()
    private val disposable = CompositeDisposable()


    val countries = MutableLiveData<List<CountryModel>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        getDataFromAPI()
    }

    private fun getDataFromAPI(){
        countryLoading.value = true
        disposable.add(
            countryService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<List<CountryModel>>(){
                    override fun onSuccess(t: List<CountryModel>) {
                        countryError.value = false
                        countryLoading.value = false
                        countries.value = t
                    }

                    override fun onError(e: Throwable) {
                        countryError.value = true
                        countryLoading.value = false
                    }

                })
        )

    }


}