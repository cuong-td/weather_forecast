package com.nab.weatherforecast.data.repository

import com.nab.weatherforecast.entity.Either
import com.nab.weatherforecast.entity.ForecastInfo
import kotlinx.coroutines.flow.Flow

interface WeatherForecastRepository {
    suspend fun fetchDailyForecast(): Flow<Either<Error, ForecastInfo>>
}