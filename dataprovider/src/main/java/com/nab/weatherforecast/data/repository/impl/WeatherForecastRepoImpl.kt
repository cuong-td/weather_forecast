package com.nab.weatherforecast.data.repository.impl

import com.nab.weatherforecast.data.local.LocalSource
import com.nab.weatherforecast.data.remote.RemoteSource
import com.nab.weatherforecast.data.repository.WeatherForecastRepository
import com.nab.weatherforecast.entity.Either
import com.nab.weatherforecast.entity.ForecastInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherForecastRepoImpl
@Inject
constructor(
    private val remoteSource: RemoteSource,
    private val localSource: LocalSource? = null,
) : WeatherForecastRepository {
    override suspend fun fetchDailyForecast(): Flow<Either<Error, ForecastInfo>> = flow {

    }
}