package com.nab.weatherforecast.data.repository.impl

import com.nab.weatherforecast.data.local.LocalSource
import com.nab.weatherforecast.data.mapper.toInfo
import com.nab.weatherforecast.data.mapper.toLocalError
import com.nab.weatherforecast.data.remote.RemoteSource
import com.nab.weatherforecast.data.remote.request.TemperatureUnit
import com.nab.weatherforecast.data.repository.WeatherForecastRepository
import com.nab.weatherforecast.entity.*
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

            val unit = TemperatureUnit.CELSIUS
            val request = mapOf(
                "q" to keyword,
                "cnt" to "7",
                "appid" to "60c6fbeb4b93ac653c492ba806fc346d",
                "units" to unit.value
            )
            var city: Pair<Long, String>? = null
            val either = safeExecution {
                val resp = remoteSource.fetchDailyForecast(request)
                city = resp.city?.let { (it.id ?: 0) to (it.name ?: "") }
                resp.list?.map { it.toInfo(unit) }?.let { it.right() } ?: throw NullPointerException()
            }
            emit(either)
            if (either is Either.Right) {
                city?.run {
                    localSource.saveCachedDailyForecast(
                        keyword,
                        first,
                        second,
                        timestamp,
                        either.right
                    )
                }
            }
            localSource.deleteOldCached(timestamp)
        }

    private inline fun <T> safeExecution(execute: () -> Either.Right<Nothing, T>): Either<Error, T> {
        runCatching {
            return execute()
        }.onFailure {
            return it.toLocalError().left()
        }
        return Error.unknown().left()
    }
}