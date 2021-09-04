package com.nab.weatherforecast.usecase.di

import com.nab.weatherforecast.data.repository.WeatherForecastRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UseCasesModule::class])
interface UseCasesComponent : UseCasesExposeProvider {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindWeatherForecastRepo(repo: WeatherForecastRepository): Builder
        fun build(): UseCasesComponent
    }
}