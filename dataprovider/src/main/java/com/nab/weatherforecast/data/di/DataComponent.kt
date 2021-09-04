package com.nab.weatherforecast.data.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class])
interface DataComponent : DataExposeApiProvider {
    @Component.Builder
    interface Builder {
        fun build(): DataComponent
    }
}