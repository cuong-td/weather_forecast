package com.nab.weatherforecast.di

import com.nab.weatherforecast.features.di.ViewModelsModule
import com.nab.weatherforecast.usecase.usecases.UseCases
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelsModule::class,
        AppModule::class,
        CommonModule::class
    ]
)
interface AppComponent : ActivityInjectionProvider {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindUseCases(useCases: UseCases): Builder

        fun build(): AppComponent
    }
}