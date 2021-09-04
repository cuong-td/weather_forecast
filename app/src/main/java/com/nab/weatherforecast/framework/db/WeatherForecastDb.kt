package com.nab.weatherforecast.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nab.weatherforecast.framework.db.entity.CityQuery
import com.nab.weatherforecast.framework.db.entity.Forecast

@Database(entities = [Forecast::class, CityQuery::class], version = 1, exportSchema = false)
abstract class WeatherForecastDb : RoomDatabase() {
    abstract val forecastDao: ForecastDao
    abstract val cityQueryDao: CityQueryDao

    companion object {
        private const val DATABASE_NAME = "weather_forecast_db"
        fun buildDatabase(context: Context): WeatherForecastDb =
            Room.databaseBuilder(context, WeatherForecastDb::class.java, DATABASE_NAME).build()
    }
}