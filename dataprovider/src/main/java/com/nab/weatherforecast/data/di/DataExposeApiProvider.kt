package com.nab.weatherforecast.data.di

import com.nab.weatherforecast.data.repository.WeatherForecastRepository

interface DataExposeApiProvider {
    val weatherForecastRepo: WeatherForecastRepository
}