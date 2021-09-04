package com.nab.weatherforecast.data.mapper

import com.nab.weatherforecast.data.remote.response.Forecast
import com.nab.weatherforecast.entity.ForecastInfo

fun Forecast.toInfo(): ForecastInfo = ForecastInfo(
    dt ?: 0,
    temp?.let { ((it.min?.toInt() ?: 0) + (it.max?.toInt() ?: 0)) / 2 }.toString(),
    pressure?.toString() ?: "",
    humidity?.toString() ?: "",
    weather?.joinToString(", ") {
        it.description ?: ""
    } ?: "No description"
)