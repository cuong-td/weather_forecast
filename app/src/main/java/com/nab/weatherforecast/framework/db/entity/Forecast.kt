package com.nab.weatherforecast.framework.db.entity

import androidx.room.Entity
import com.nab.weatherforecast.entity.ForecastInfo

@Entity(tableName = "forecast", primaryKeys = ["cityId", "timestamp"])
data class Forecast(
    val cityId: Long = 0L,
    val timestamp: Long = 0L,
    val averageTemperature: Int,
    val pressure: Int,
    val humidity: Int,
    val description: String,
    val tempSign: String
) {
    fun toInfo(): ForecastInfo = ForecastInfo(
        timestamp / 1000,
        averageTemperature,
        pressure,
        humidity,
        description,
        tempSign
    )

    companion object {
        fun from(info: ForecastInfo, cityId: Long): Forecast = Forecast(
            cityId,
            info.timestampInSeconds * 1000,
            info.averageTemperature,
            info.pressure,
            info.humidity,
            info.description,
            info.temperatureSign
        )
    }
}