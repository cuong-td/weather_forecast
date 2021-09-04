package com.nab.weatherforecast.di

import androidx.lifecycle.ViewModelProvider
import com.nab.weatherforecast.features.di.ViewModelFactory
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindViewModelFactory(vmFactory: ViewModelFactory): ViewModelProvider.Factory
}