package com.nab.weatherforecast.entity

data class ForecastInfo(
    val timestampInSeconds: Long,
    val averageTemperature: Int,
    val pressure: Int,
    val humidity: Int,
    val description: String,
    val temperatureSign: String = ""
)