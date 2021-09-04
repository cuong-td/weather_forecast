package com.nab.weatherforecast.framework.db

import com.nab.weatherforecast.data.local.LocalSource
import com.nab.weatherforecast.entity.ForecastInfo
import com.nab.weatherforecast.framework.db.entity.CityQuery
import com.nab.weatherforecast.framework.db.entity.Forecast
import javax.inject.Inject

class LocalSourceImpl
@Inject
constructor(private val db: WeatherForecastDb) : LocalSource {
    override suspend fun loadCachedDailyForecast(
        keyword: String,
        timestamp: Long
    ): List<ForecastInfo> =
        db.forecastDao.loadForecasts(keyword, timestamp)
            .map { it.toInfo() }

    override suspend fun saveCachedDailyForecast(
        keyword: String,
        cityId: Long,
        name: String,
        queryTimestamp: Long,
        list: List<ForecastInfo>
    ) {
        db.forecastDao.storeForecasts(list.map {
            Forecast.from(it, cityId)
        })
        db.cityQueryDao.storeCityQuery(CityQuery(cityId, keyword, name, queryTimestamp))
    }

    override suspend fun deleteOldCached(timestamp: Long) {
        db.forecastDao.deleteOldForecast(timestamp)
        db.cityQueryDao.deleteOldQuery(timestamp)
    }

}