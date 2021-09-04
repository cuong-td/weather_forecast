package com.nab.weatherforecast.data.mapper

import com.nab.weatherforecast.data.remote.request.TemperatureUnit
import com.nab.weatherforecast.data.remote.response.Forecast
import com.nab.weatherforecast.entity.Error
import com.nab.weatherforecast.entity.ForecastInfo
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

fun Forecast.toInfo(unit: TemperatureUnit): ForecastInfo = ForecastInfo(
    dt ?: 0,
    temp?.let { ((it.min ?: 0.0) + (it.max ?: 0.0)) / 2 }?.toInt() ?: 0,
    pressure ?: 0,
    humidity ?: 0,
    weather?.joinToString(", ") {
        it.description ?: ""
    } ?: "No description",
    unit.sign
)

fun Throwable.toLocalError(): Error = when (this) {
    is IOException, is UnknownHostException -> Error.network()
    is HttpException -> Error.errorData(code(), message(), response())
    else -> Error.unknown()
}