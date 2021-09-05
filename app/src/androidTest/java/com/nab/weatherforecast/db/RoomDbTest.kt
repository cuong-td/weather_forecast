package com.nab.weatherforecast.db

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.nab.weatherforecast.framework.db.WeatherForecastDb
import com.nab.weatherforecast.framework.db.entity.CityQuery
import com.nab.weatherforecast.framework.db.entity.Forecast
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class RoomDbTest {
    private lateinit var db: WeatherForecastDb

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        db = Room.inMemoryDatabaseBuilder(context, WeatherForecastDb::class.java).build()
    }

    @After
    @Throws(IOException::class)
    fun close() {
        db.close()
    }

    @Test
    fun test() {
        val listForecast = List(7) {
            genForecast(1000 + it.toLong())
        }
        val city = genCity()
        runBlocking {
            db.forecastDao.storeForecasts(listForecast)
            db.forecastDao.storeForecasts(List(3) { genForecast(999 - it.toLong()) })
            db.cityQueryDao.storeCityQuery(city)

            var query = db.forecastDao.loadForecasts("saigon", 900)
            assertEquals(10, query.size)

            // Allow search by name
            query = db.forecastDao.loadForecasts("Ho Chi Minh City", 900)
            assertEquals(10, query.size)

            with(query.first()) {
                assertEquals(1, cityId)
                assertEquals(30, averageTemperature)
                assertEquals(81, pressure)
                assertEquals(1009, humidity)
                assertEquals("test", description)
            }

            db.forecastDao.deleteOldForecast(1000)
            query = db.forecastDao.loadForecasts("saigon", 900)
            assertEquals(7, query.size)

            db.cityQueryDao.deleteOldQuery(1001)
            query = db.forecastDao.loadForecasts("saigon", 900)
            assertTrue(query.isEmpty())
        }
    }

    private fun genForecast(time: Long = 1000L) = Forecast(
        cityId = 1L,
        timestamp = time,
        averageTemperature = 30,
        pressure = 81,
        humidity = 1009,
        description = "test",
        tempSign = ""
    )

    private fun genCity(time: Long = 1000L) = CityQuery(
        cityId = 1L,
        timestamp = time,
        name = "Ho Chi Minh City",
        query = "saigon"
    )
}