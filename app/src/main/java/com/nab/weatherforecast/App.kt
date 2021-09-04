package com.nab.weatherforecast

import android.app.Application
import com.nab.weatherforecast.framework.di.ActivityInjectionProvider
import com.nab.weatherforecast.framework.di.InjectorProvider

class App : Application(), InjectorProvider {
    private lateinit var diDelegate: DiDelegate
    override fun onCreate() {
        super.onCreate()
        diDelegate = DiDelegate(this)
    }

    override fun activityInjector(): ActivityInjectionProvider = diDelegate.appComponent
}