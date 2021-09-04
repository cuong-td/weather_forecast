package com.nab.weatherforecast.features.forecast

import androidx.databinding.ObservableField
import com.nab.weatherforecast.entity.ForecastInfo
import com.nab.weatherforecast.ext.displayTimestamp

class ForecastItemBindingModel {
    val date = ObservableField<String>()
    val aveTemp = ObservableField<String>()
    val pressure = ObservableField<String>()
    val humidity = ObservableField<String>()
    val desc = ObservableField<String>()

    fun bind(info: ForecastInfo) {
        date.set(displayTimestamp(info.timestampInSeconds * 1000))
        aveTemp.set("${info.averageTemperature}${info.temperatureSign}")
        pressure.set("${info.pressure}")
        humidity.set("${info.humidity}%")
        desc.set(info.description)
    }
}