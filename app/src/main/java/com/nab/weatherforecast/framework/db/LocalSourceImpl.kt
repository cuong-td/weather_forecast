package com.nab.weatherforecast.framework.db

import com.nab.weatherforecast.data.local.LocalSource
import javax.inject.Inject

class LocalSourceImpl
@Inject
constructor(private val db: WeatherForecastDb) : LocalSource {

    override suspend fun loadCachedDailyForecast() {
    }

    override suspend fun saveCachedDailyForecast() {
    }
}