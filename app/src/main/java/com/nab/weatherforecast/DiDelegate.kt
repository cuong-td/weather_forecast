package com.nab.weatherforecast

import com.nab.weatherforecast.data.di.DaggerDataComponent
import com.nab.weatherforecast.data.di.DataComponent
import com.nab.weatherforecast.framework.di.AppComponent
import com.nab.weatherforecast.framework.di.DaggerAppComponent

class DiDelegate(private val app: App) {
    private val dataComponent: DataComponent by lazy {
        DaggerDataComponent.create()
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(app)
            .weatherForecastRepo(dataComponent.getWeatherForecastRepo())
            .build()
    }
}