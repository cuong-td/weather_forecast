package com.nab.weatherforecast.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class CommonModule {

    @Provides
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO
}