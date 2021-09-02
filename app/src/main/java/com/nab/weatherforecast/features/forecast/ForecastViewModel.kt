package com.nab.weatherforecast.features.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nab.weatherforecast.data.ForecastInfo

class ForecastViewModel : ViewModel() {
    val state: LiveData<ForecastState> = MutableLiveData()

    fun test() {
        (state as? MutableLiveData)?.let {
            it.value = ForecastState.SuccessState(
                List(5) {
                    ForecastInfo(
                        0,
                        "",
                        "",
                        "",
                        ""
                    )
                }
            )
        }
    }
}