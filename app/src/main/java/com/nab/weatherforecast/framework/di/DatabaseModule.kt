package com.nab.weatherforecast.framework.di

import android.content.Context
import com.nab.weatherforecast.data.local.LocalSource
import com.nab.weatherforecast.framework.db.LocalSourceImpl
import com.nab.weatherforecast.framework.db.WeatherForecastDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDb(context: Context): WeatherForecastDb = WeatherForecastDb.buildDatabase(context)

    @Provides
    @Singleton
    fun provideLocalSource(impl: LocalSourceImpl): LocalSource = impl
}