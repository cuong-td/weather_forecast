package com.nab.weatherforecast

import com.nab.weatherforecast.di.AppComponent
import com.nab.weatherforecast.di.DaggerAppComponent

class DiDelegate(private val app: App) {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(app)
            .build()
    }
}