package com.nab.weatherforecast.data.mapper

import com.nab.weatherforecast.data.remote.response.Forecast
import com.nab.weatherforecast.entity.Error
import com.nab.weatherforecast.entity.ForecastInfo
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

fun Forecast.toInfo(): ForecastInfo = ForecastInfo(
    dt ?: 0,
    temp?.let { ((it.min?.toInt() ?: 0) + (it.max?.toInt() ?: 0)) / 2 }.toString(),
    pressure?.toString() ?: "",
    humidity?.toString() ?: "",
    weather?.joinToString(", ") {
        it.description ?: ""
    } ?: "No description"
)

fun Throwable.toLocalError(): Error = when (this) {
    is IOException, is UnknownHostException -> Error.network()
    is HttpException -> Error.errorData(code(), message(), response())
    else -> Error.unknown()
}