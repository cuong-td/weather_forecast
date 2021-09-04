package com.nab.weatherforecast.framework.di

import androidx.lifecycle.ViewModelProvider
import com.nab.weatherforecast.data.local.LocalSource
import com.nab.weatherforecast.features.di.ViewModelFactory
import com.nab.weatherforecast.framework.db.LocalSourceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindLocalSource(impl: LocalSourceImpl): LocalSource

    @Binds
    @Singleton
    abstract fun bindViewModelFactory(vmFactory: ViewModelFactory): ViewModelProvider.Factory
}