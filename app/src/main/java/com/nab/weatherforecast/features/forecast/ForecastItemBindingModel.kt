package com.nab.weatherforecast.features.forecast

import androidx.databinding.ObservableField
import com.nab.weatherforecast.entity.ForecastInfo

class ForecastItemBindingModel {
    val date = ObservableField<String>()
    val aveTemp = ObservableField<String>()
    val pressure = ObservableField<String>()
    val humidity = ObservableField<String>()
    val desc = ObservableField<String>()

    fun bind(info: ForecastInfo) {
        date.set("Tue, 10 Mar 2020")
        aveTemp.set(info.averageTemperature)
        pressure.set(info.pressure)
        humidity.set(info.humidity)
        desc.set(info.description)
    }
}