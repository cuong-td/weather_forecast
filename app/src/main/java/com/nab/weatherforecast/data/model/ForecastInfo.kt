package com.nab.weatherforecast.data.model

data class ForecastInfo(
    val timeStamp: Long,
    val averageTemperature: String,
    val pressure: String,
    val humidity: String,
    val description: String
)