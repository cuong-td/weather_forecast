package com.nab.weatherforecast.data.repository

import com.nab.weatherforecast.entity.Either
import com.nab.weatherforecast.entity.Error
import com.nab.weatherforecast.entity.ForecastInfo
import kotlinx.coroutines.flow.Flow

interface WeatherForecastRepository {
    suspend fun fetchDailyForecast(keyword: String, timestamp: Long): Flow<Either<Error, List<ForecastInfo>>>
}