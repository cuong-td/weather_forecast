package com.nab.weatherforecast.data.remote.response

import com.google.gson.annotations.SerializedName

data class City(
    val coord: Coord?,
    val country: String?,
    val id: Int?,
    val name: String?,
    val population: Int?,
    val timezone: Int?
)

data class Coord(
    val lat: Double?,
    val lon: Double?
)

data class FeelsLike(
    val day: Double?,
    val eve: Double?,
    val morn: Double?,
    val night: Double?
)

data class Forecast(
    val clouds: Int?,
    val deg: Int?,
    val dt: Int?,
    val feels_like: FeelsLike?,
    val gust: Double?,
    val humidity: Int?,
    val pop: Double?,
    val pressure: Int?,
    val rain: Double?,
    val speed: Double?,
    val sunrise: Int?,
    val sunset: Int?,
    val temp: Temp?,
    val weather: List<Weather>?
)

data class Temp(
    val day: Double?,
    val eve: Double?,
    val max: Double?,
    val min: Double?,
    val morn: Double?,
    val night: Double?
)

data class Weather(
    val description: String?,
    val icon: String?,
    val id: Int?,
    val main: String?
)

data class WeatherForecastResp(
    val city: City?,
    @SerializedName("cnt")
    val count: Int?,
    @SerializedName("cod")
    val code: String?,
    val list: List<Forecast>?,
    val message: String?
)

