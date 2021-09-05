package com.nab.weatherforecast.repo

import com.nab.weatherforecast.VALID_SEARCH
import com.nab.weatherforecast.data.local.LocalSource
import com.nab.weatherforecast.data.remote.RemoteSource
import com.nab.weatherforecast.data.remote.request.TemperatureUnit
import com.nab.weatherforecast.data.repository.impl.WeatherForecastRepoImpl
import com.nab.weatherforecast.entity.Either
import com.nab.weatherforecast.entity.Error
import com.nab.weatherforecast.getWeatherResp
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class WeatherForecastRepoTest {

    @Mock
    private lateinit var localSource: LocalSource

    @Mock
    private lateinit var remoteSource: RemoteSource

    private lateinit var repo: WeatherForecastRepoImpl

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        repo = WeatherForecastRepoImpl(
            localSource = localSource,
            remoteSource = remoteSource,
            apiKey = "apiKey"
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `check success search data from remote`() {
        dispatcher.runBlockingTest {
            val keyword = VALID_SEARCH
            val time = 100L
            val request = mapOf(
                "q" to keyword,
                "cnt" to "7",
                "appid" to "apiKey",
                "units" to TemperatureUnit.KELVIN.value
            )
            `when`(localSource.loadCachedDailyForecast(keyword, time)).thenReturn(emptyList())

            `when`(remoteSource.fetchDailyForecast(request)).thenReturn(getWeatherResp())
            val either = repo.fetchDailyForecast(keyword, time, 7, TemperatureUnit.KELVIN).first()
            assertTrue(either is Either.Right)
            with((either as Either.Right).right.first()) {
                assertEquals(humidity, 1001)
                assertEquals(averageTemperature, 29)
                assertEquals(pressure, 85)
                assertEquals(timestampInSeconds, 1000)
                assertEquals(description, "Nothing here")
            }
        }
    }

    @Test
    fun `check unknown error on searching data from remote`() {
        dispatcher.runBlockingTest {
            val keyword = VALID_SEARCH
            val time = 100L
            val request = mapOf(
                "q" to keyword,
                "cnt" to "7",
                "appid" to "apiKey",
                "units" to TemperatureUnit.KELVIN.value
            )
            `when`(localSource.loadCachedDailyForecast(keyword, time)).thenReturn(emptyList())

            `when`(remoteSource.fetchDailyForecast(request)).thenThrow(NullPointerException())
            val either = repo.fetchDailyForecast(keyword, time, 7, TemperatureUnit.KELVIN).first()
            assertTrue(either is Either.Left)
            with(either as Either.Left) {
                assertEquals(Error.UNKNOWN, left.code)
            }
        }
    }
}