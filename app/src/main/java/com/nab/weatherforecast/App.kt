package com.nab.weatherforecast

import androidx.multidex.MultiDexApplication
import com.nab.weatherforecast.di.ActivityInjectionProvider
import com.nab.weatherforecast.di.InjectorProvider

class App : MultiDexApplication(), InjectorProvider {
    lateinit var diDelegate: DiDelegate
    override fun onCreate() {
        super.onCreate()
        diDelegate = DiDelegate(this)
    }

    override fun activityInjector(): ActivityInjectionProvider = diDelegate.appComponent
}