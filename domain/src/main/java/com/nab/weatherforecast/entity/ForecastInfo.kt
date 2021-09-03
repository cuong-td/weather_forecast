package com.nab.weatherforecast.entity

data class ForecastInfo(
    val timeStamp: Long,
    val averageTemperature: String,
    val pressure: String,
    val humidity: String,
    val description: String
)