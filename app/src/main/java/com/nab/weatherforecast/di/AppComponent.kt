package com.nab.weatherforecast.di

import android.app.Application
import com.nab.weatherforecast.data.repository.WeatherForecastRepository
import com.nab.weatherforecast.features.di.ViewModelsModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelsModule::class, AppModule::class, UseCasesModule::class])
interface AppComponent : ActivityInjectionProvider {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        @BindsInstance
        fun bindWeatherForecastRepo(repo: WeatherForecastRepository): Builder

        fun build(): AppComponent
    }
}