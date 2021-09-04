package com.nab.weatherforecast.usecase.usecases.impl

import com.nab.weatherforecast.data.repository.WeatherForecastRepository
import com.nab.weatherforecast.usecase.usecases.UseCases
import javax.inject.Inject

class UseCasesImpl
@Inject
constructor(private val repo: WeatherForecastRepository) : UseCases {
    override suspend fun getDailyForecast(
        keyword: String,
        timestamp: Long
    ) = repo.fetchDailyForecast(keyword, timestamp)
}