package com.nab.weatherforecast.usecase.usecases

import com.nab.weatherforecast.data.repository.WeatherForecastRepository

class GetDailyForecast(private val repo: WeatherForecastRepository) {
    suspend operator fun invoke(keyword: String, timestamp: Long) = repo.fetchDailyForecast(keyword, timestamp)
}