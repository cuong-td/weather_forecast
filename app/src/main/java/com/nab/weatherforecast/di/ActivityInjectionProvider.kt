package com.nab.weatherforecast.di

import com.nab.weatherforecast.features.forecast.ForecastActivity

interface ActivityInjectionProvider {
    fun inject(activity: ForecastActivity)
}