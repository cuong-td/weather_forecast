package com.nab.weatherforecast.data.repository

import com.nab.weatherforecast.data.model.Either
import com.nab.weatherforecast.data.model.ForecastInfo
import kotlinx.coroutines.flow.Flow

interface WeatherForecastRepository {
    suspend fun fetchDailyForecast(): Flow<Either<Error, ForecastInfo>>
}