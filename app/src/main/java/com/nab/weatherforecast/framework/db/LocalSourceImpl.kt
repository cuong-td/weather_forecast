package com.nab.weatherforecast.framework.db

import com.nab.weatherforecast.data.local.LocalSource

class LocalSourceImpl : LocalSource {

    override suspend fun loadCachedDailyForecast() {
    }

    override suspend fun saveCachedDailyForecast() {
    }
}