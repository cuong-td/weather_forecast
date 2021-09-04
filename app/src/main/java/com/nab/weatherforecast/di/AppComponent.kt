package com.nab.weatherforecast.di

import android.content.Context
import com.nab.weatherforecast.data.repository.WeatherForecastRepository
import com.nab.weatherforecast.features.di.ViewModelsModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelsModule::class,
        AppModule::class,
        UseCasesModule::class,
        CommonModule::class
    ]
)
interface AppComponent : ActivityInjectionProvider {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindContext(context: Context): Builder

        @BindsInstance
        fun bindWeatherForecastRepo(repo: WeatherForecastRepository): Builder

        fun build(): AppComponent
    }
}