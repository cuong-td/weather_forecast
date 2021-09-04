package com.nab.weatherforecast.features.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nab.weatherforecast.features.forecast.ForecastViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(ForecastViewModel::class)
    abstract fun bindForecastViewModel(vm: ForecastViewModel): ViewModel

    @Binds
    @Singleton
    abstract fun bindVmFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}