package com.nab.weatherforecast.usecase

import com.nab.weatherforecast.data.repository.WeatherForecastRepository

class GetDailyForecast(private val repo: WeatherForecastRepository) {
    suspend operator fun invoke() = repo.fetchDailyForecast()
}