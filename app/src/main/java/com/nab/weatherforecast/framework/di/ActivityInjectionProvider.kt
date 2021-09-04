package com.nab.weatherforecast.framework.di

import com.nab.weatherforecast.features.forecast.ForecastActivity

interface ActivityInjectionProvider {
    fun inject(activity: ForecastActivity)
}