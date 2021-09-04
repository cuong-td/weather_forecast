package com.nab.weatherforecast.usecase.usecases

import com.nab.weatherforecast.entity.Either
import com.nab.weatherforecast.entity.Error
import com.nab.weatherforecast.entity.ForecastInfo
import kotlinx.coroutines.flow.Flow

interface UseCases {
    suspend fun getDailyForecast(
        keyword: String,
        timestamp: Long
    ): Flow<Either<Error, List<ForecastInfo>>>
}