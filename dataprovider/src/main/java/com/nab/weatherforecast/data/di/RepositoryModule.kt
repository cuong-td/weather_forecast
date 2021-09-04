package com.nab.weatherforecast.data.di

import com.nab.weatherforecast.data.repository.WeatherForecastRepository
import com.nab.weatherforecast.data.repository.impl.WeatherForecastRepoImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindWeatherForecastRepo(impl: WeatherForecastRepoImpl): WeatherForecastRepository
}