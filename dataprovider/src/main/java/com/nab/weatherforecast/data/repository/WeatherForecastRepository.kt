package com.nab.weatherforecast.data.repository

import com.nab.weatherforecast.domain.entity.Either
import com.nab.weatherforecast.domain.entity.ForecastInfo
import kotlinx.coroutines.flow.Flow

interface WeatherForecastRepository {
    suspend fun fetchDailyForecast(): Flow<Either<Error, ForecastInfo>>
}