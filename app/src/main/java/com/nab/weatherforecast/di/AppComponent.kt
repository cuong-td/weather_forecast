package com.nab.weatherforecast.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component

@Component
interface AppComponent : ActivityInjectionProvider {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }
}