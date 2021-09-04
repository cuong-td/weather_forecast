package com.nab.weatherforecast.data.local

import com.nab.weatherforecast.entity.ForecastInfo

interface LocalSource {
    suspend fun loadCachedDailyForecast(keyword: String, timestamp: Long): List<ForecastInfo>
    suspend fun saveCachedDailyForecast(
        keyword: String,
        cityId: Long,
        name: String,
        queryTimestamp: Long,
        list: List<ForecastInfo>
    )
    suspend fun deleteOldCached(timestamp: Long)
}