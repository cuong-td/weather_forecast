package com.nab.weatherforecast.entity

data class ForecastInfo(
    val timestampInSeconds: Long,
    val averageTemperature: String,
    val pressure: String,
    val humidity: String,
    val description: String
)