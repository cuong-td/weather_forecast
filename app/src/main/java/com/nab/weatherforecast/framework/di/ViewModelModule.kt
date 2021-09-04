package com.nab.weatherforecast.framework.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindVmFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}