package com.nab.weatherforecast.di

import com.nab.weatherforecast.data.repository.WeatherForecastRepository
import com.nab.weatherforecast.framework.UseCases
import com.nab.weatherforecast.usecase.GetDailyForecast
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCasesModule {
    @Provides
    @Singleton
    fun provideUseCases(repo: WeatherForecastRepository): UseCases =
        UseCases(GetDailyForecast(repo))
}