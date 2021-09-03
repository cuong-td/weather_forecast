package com.nab.weatherforecast.data.remote

import com.nab.weatherforecast.data.remote.response.WeatherForecastResp
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RemoteSource {
    @GET
    suspend fun fetchDailyForecast(@QueryMap request: Map<String, String>): WeatherForecastResp
}