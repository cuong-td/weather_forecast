package com.nab.weatherforecast.features.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nab.weatherforecast.base.BaseViewModel
import com.nab.weatherforecast.domain.entity.ForecastInfo
import kotlin.random.Random

class ForecastViewModel : BaseViewModel() {
    val state: LiveData<ForecastState> = MutableLiveData()

    fun test() {
        state.setData(ForecastState.SuccessState(
            List(Random.nextInt(7, 10)) {
                ForecastInfo(
                    0,
                    "${Random.nextInt(25, 40)}\u2103",
                    "${Random.nextInt(600, 2000)}",
                    "${Random.nextInt(60, 100)}%",
                    "Test is okay"
                )
            }
        ))
    }
}