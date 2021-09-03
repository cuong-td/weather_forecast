package com.nab.weatherforecast.data.remote.request

import com.google.gson.annotations.SerializedName

enum class TemperatureUnit {
    @SerializedName("default")
    KELVIN,
    @SerializedName("metric")
    CELSIUS,
    @SerializedName("imperial")
    FAHRENHEIT
}