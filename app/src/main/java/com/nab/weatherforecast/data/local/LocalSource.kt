package com.nab.weatherforecast.data.local

interface LocalSource {
    suspend fun loadCachedDailyForecast()
    suspend fun saveCachedDailyForecast()
}