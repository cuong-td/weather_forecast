package com.nab.weatherforecast.framework.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nab.weatherforecast.framework.db.entity.Forecast

@Dao
interface ForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeForecasts(forecasts: List<Forecast>)

    @Query("select forecast.cityId, forecast.timestamp, averageTemperature, pressure, humidity, description, tempSign from forecast inner join city_query on forecast.cityId = city_query.cityId where (`query` like :query or name like :query) and city_query.timestamp >= :timestamp and forecast.timestamp >= :timestamp")
    suspend fun loadForecasts(query: String, timestamp: Long): List<Forecast>

    @Query("delete from forecast where timestamp < :timestamp")
    suspend fun deleteOldForecast(timestamp: Long)

    @Query("delete from forecast")
    suspend fun clearForecastCache()
}