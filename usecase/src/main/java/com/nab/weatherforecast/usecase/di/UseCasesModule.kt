package com.nab.weatherforecast.usecase.di

import com.nab.weatherforecast.usecase.usecases.UseCases
import com.nab.weatherforecast.usecase.usecases.impl.UseCasesImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class UseCasesModule {
    @Binds
    @Singleton
    abstract fun bindUseCases(impl: UseCasesImpl): UseCases
}