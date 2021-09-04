package com.nab.weatherforecast.data.remote.request

enum class TemperatureUnit(val value: String, val sign: String = "") {
    KELVIN(
        value = "default",
    ),

    CELSIUS(
        value = "metric",
        sign = "\u2103"
    ),

    FAHRENHEIT(
        value = "imperial"
    )
}