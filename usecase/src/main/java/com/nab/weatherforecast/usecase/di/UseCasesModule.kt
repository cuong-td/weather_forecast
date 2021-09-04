package com.nab.weatherforecast.usecase.di

import com.nab.weatherforecast.data.repository.WeatherForecastRepository
import com.nab.weatherforecast.usecase.UseCases
import com.nab.weatherforecast.usecase.usecases.GetDailyForecast
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