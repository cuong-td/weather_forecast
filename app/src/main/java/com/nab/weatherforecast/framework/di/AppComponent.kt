package com.nab.weatherforecast.framework.di

import android.app.Application
import com.nab.weatherforecast.data.repository.WeatherForecastRepository
import com.nab.weatherforecast.features.di.ViewModelsModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelsModule::class, UseCasesModule::class, AppModule::class])
interface AppComponent : ActivityInjectionProvider {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        @BindsInstance
        fun weatherForecastRepo(repo: WeatherForecastRepository): Builder

        fun build(): AppComponent
    }
}