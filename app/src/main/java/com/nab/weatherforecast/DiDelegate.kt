package com.nab.weatherforecast

import com.nab.weatherforecast.data.di.DaggerDataComponent
import com.nab.weatherforecast.data.di.DataComponent
import com.nab.weatherforecast.di.AppComponent
import com.nab.weatherforecast.di.DaggerAppComponent
import com.nab.weatherforecast.framework.di.DaggerFrameworkComponent
import com.nab.weatherforecast.framework.di.FrameworkComponent
import com.nab.weatherforecast.usecase.di.DaggerUseCasesComponent
import com.nab.weatherforecast.usecase.di.UseCasesComponent

class DiDelegate(private val app: App) {
    private val frameworkComponent: FrameworkComponent by lazy {
        DaggerFrameworkComponent.builder()
            .bindContext(app)
            .build()
    }

    private val dataComponent: DataComponent by lazy {
        DaggerDataComponent.builder()
            .bindLocalSource(frameworkComponent.localSource)
            .build()
    }

    private val useCasesComponent: UseCasesComponent by lazy {
        DaggerUseCasesComponent.builder()
            .bindWeatherForecastRepo(dataComponent.weatherForecastRepo)
            .build()
    }
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .bindUseCases(useCasesComponent.useCases)
            .build()
    }

}