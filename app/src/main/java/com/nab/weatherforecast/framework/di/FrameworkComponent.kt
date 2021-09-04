package com.nab.weatherforecast.framework.di

import android.content.Context
import com.nab.weatherforecast.data.local.LocalSource
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class])
interface FrameworkComponent {
    val localSource: LocalSource

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindContext(context: Context): Builder

        fun build(): FrameworkComponent
    }
}