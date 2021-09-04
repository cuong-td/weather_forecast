package com.nab.weatherforecast.data.repository

import com.nab.weatherforecast.data.remote.request.TemperatureUnit
import com.nab.weatherforecast.entity.Either
import com.nab.weatherforecast.entity.Error
import com.nab.weatherforecast.entity.ForecastInfo
import kotlinx.coroutines.flow.Flow

interface WeatherForecastRepository {
    suspend fun fetchDailyForecast(
        keyword: String,
        timestamp: Long,
        dayCount: Int = 7,
        unit: TemperatureUnit = TemperatureUnit.CELSIUS,
    ): Flow<Either<Error, List<ForecastInfo>>>
}