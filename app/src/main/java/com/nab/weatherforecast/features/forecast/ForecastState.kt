package com.nab.weatherforecast.features.forecast

import com.nab.weatherforecast.entity.Either
import com.nab.weatherforecast.entity.ForecastInfo

sealed class ForecastState {
    object LoadingState : ForecastState()
    data class ErrorState(val either: Either<Int, String>) : ForecastState()
    data class SuccessState(val data: List<ForecastInfo>) : ForecastState()
}