package com.nab.weatherforecast.data.di

import com.nab.weatherforecast.data.repository.WeatherForecastRepository
import com.nab.weatherforecast.data.repository.impl.WeatherForecastRepoImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindWeatherForecastRepo(impl: WeatherForecastRepoImpl): WeatherForecastRepository
}