package com.semihozmen.lkelerkotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(application: Application):AndroidViewModel(application),CoroutineScope {

    private val job  = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main                     // Arkaplanda işi yap maine dön

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}