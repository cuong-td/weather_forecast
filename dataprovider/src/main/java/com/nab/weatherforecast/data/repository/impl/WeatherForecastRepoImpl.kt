package com.nab.weatherforecast.data.repository.impl

import com.nab.weatherforecast.data.mapper.toInfo
import com.nab.weatherforecast.data.remote.RemoteSource
import com.nab.weatherforecast.data.repository.WeatherForecastRepository
import com.nab.weatherforecast.entity.Either
import com.nab.weatherforecast.entity.ForecastInfo
import com.nab.weatherforecast.entity.right
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherForecastRepoImpl
@Inject
constructor(
    private val remoteSource: RemoteSource,
//    private val localSource: LocalSource? = null,
) : WeatherForecastRepository {
    override suspend fun fetchDailyForecast(): Flow<Either<Error, List<ForecastInfo>>> = flow {
        val request = mapOf(
            "q" to "saigon",
            "cnt" to "7",
            "appid" to "60c6fbeb4b93ac653c492ba806fc346d",
            "units" to "metric"
        )
        val resp = remoteSource.fetchDailyForecast(request)
        resp.list?.map { it.toInfo() }?.let {
            emit(it.right())
        }
    }
}