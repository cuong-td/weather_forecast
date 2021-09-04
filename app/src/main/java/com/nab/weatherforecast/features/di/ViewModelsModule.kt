package com.nab.weatherforecast.features.di

import androidx.lifecycle.ViewModel
import com.nab.weatherforecast.features.forecast.ForecastViewModel
import com.nab.weatherforecast.framework.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(ForecastViewModel::class)
    abstract fun bindForecastViewModel(vm: ForecastViewModel): ViewModel
}