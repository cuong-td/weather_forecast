package com.nab.weatherforecast.data.di

import dagger.Component

@Component(modules = [NetworkModule::class])
interface DataComponent {
    @Component.Builder
    interface Builder {
        fun build(): DataComponent
    }
}