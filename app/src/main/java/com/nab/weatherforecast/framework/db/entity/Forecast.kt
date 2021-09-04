package com.nab.weatherforecast.framework.db.entity

import androidx.room.Entity
import com.nab.weatherforecast.entity.ForecastInfo

@Entity(tableName = "forecast", primaryKeys = ["cityId", "timestamp"])
data class Forecast(
    val cityId: Long = 0L,
    val timestamp: Long = 0L,
    val averageTemperature: Double,
    val pressure: Int,
    val humidity: Int,
    val description: String
) {
    fun toInfo(): ForecastInfo = ForecastInfo(
        timestamp,
        averageTemperature.toString(),
        pressure.toString(),
        humidity.toString(),
        description
    )

    companion object {
//        fun fromInfo(info: ForecastInfo, cityId: Long): Forecast = Forecast(
//            cityId,
//            info.timestampInSeconds,
//            info.averageTemperature.toDouble(),
//
//        )
    }
}