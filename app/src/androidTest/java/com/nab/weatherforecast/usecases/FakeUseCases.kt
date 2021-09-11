package com.nab.weatherforecast.usecases

import com.nab.weatherforecast.entity.*
import com.nab.weatherforecast.usecase.usecases.UseCases
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeUseCases : UseCases {
    override suspend fun getDailyForecast(
        keyword: String,
        timestamp: Long
    ): Flow<Either<Error, List<ForecastInfo>>> = flow {
        delay(2000)
        when (keyword.toLowerCase()) {
            "sai" -> emit(validSearchResult().right())
            "saigo" -> emit(Error.errorData(404, "").left())
            else -> emit(Error.unknown().left())
        }
    }

    private fun validSearchResult(size: Int = 7): List<ForecastInfo> = List(size) {
        ForecastInfo(
            1630773376,
            30,
            1001,
            85,
            "Nothing special",
            "\u2103"
        )
    }
}