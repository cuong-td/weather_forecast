package com.nab.weatherforecast.features.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nab.weatherforecast.data.model.ForecastInfo
import kotlin.random.Random

class ForecastViewModel : ViewModel() {
    val state: LiveData<ForecastState> = MutableLiveData()

    fun test() {
        (state as? MutableLiveData)?.let {
            it.value = ForecastState.SuccessState(
                List(5) {
                    ForecastInfo(
                        0,
                        "${Random.nextInt(25, 40)}\u2103",
                        "${Random.nextInt(600, 2000)}",
                        "${Random.nextInt(60, 100)}%",
                        "Test is okay"
                    )
                }
            )
        }
    }
}