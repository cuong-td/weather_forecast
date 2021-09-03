package com.nab.weatherforecast

import android.app.Application

class App : Application() {
    private lateinit var diDelegate: DiDelegate
    override fun onCreate() {
        super.onCreate()
        diDelegate = DiDelegate(this)
    }
}