package com.nab.weatherforecast.data.di

import com.nab.weatherforecast.data.local.LocalSource
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class])
interface DataComponent : DataExposeApiProvider {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindLocalSource(localSource: LocalSource)

        fun build(): DataComponent
    }
}