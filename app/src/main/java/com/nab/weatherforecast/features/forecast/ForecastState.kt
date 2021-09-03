package com.nab.weatherforecast.features.forecast

import com.nab.weatherforecast.data.model.ForecastInfo

sealed class ForecastState {
    object LoadingState : ForecastState()
    data class ErrorState(val data: String) : ForecastState()
    data class SuccessState(val data: List<ForecastInfo>) : ForecastState()
}