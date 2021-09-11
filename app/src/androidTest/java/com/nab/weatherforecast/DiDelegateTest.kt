package com.nab.weatherforecast

import com.nab.weatherforecast.di.AppComponent
import com.nab.weatherforecast.di.DaggerAppComponent
import com.nab.weatherforecast.usecase.usecases.UseCases

class DiDelegateTest(app: App, private val useCases: UseCases) : DiDelegate(app) {
    override val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .bindUseCases(useCases)
            .build()
    }
}