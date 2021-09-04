package com.nab.weatherforecast.data.repository.impl

import com.nab.weatherforecast.data.local.LocalSource
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
    private val localSource: LocalSource,
) : WeatherForecastRepository {
    override suspend fun fetchDailyForecast(
        keyword: String,
        timestamp: Long
    ): Flow<Either<Error, List<ForecastInfo>>> =
        flow {
            val cachedForecast = localSource.loadCachedDailyForecast(keyword, timestamp)
            if (cachedForecast.isNotEmpty()) {
                emit(cachedForecast.right())
                return@flow
            }

            val request = mapOf(
                "q" to keyword,
                "cnt" to "7",
                "appid" to "60c6fbeb4b93ac653c492ba806fc346d",
                "units" to "metric"
            )
            val resp = remoteSource.fetchDailyForecast(request)
            val city = resp.city?.let { (it.id ?: 0) to (it.name ?: "") }
            resp.list?.map { it.toInfo() }?.let {
                emit(it.right())
                city?.run {
                    localSource.saveCachedDailyForecast(
                        keyword,
                        first,
                        second,
                        timestamp,
                        it
                    )
                }
            }
            localSource.deleteOldCached(timestamp)
        }
}