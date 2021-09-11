package com.nab.weatherforecast.usecases

import com.nab.weatherforecast.VALID_SEARCH
import com.nab.weatherforecast.data.repository.WeatherForecastRepository
import com.nab.weatherforecast.entity.Either
import com.nab.weatherforecast.entity.Error
import com.nab.weatherforecast.entity.left
import com.nab.weatherforecast.entity.right
import com.nab.weatherforecast.usecase.usecases.impl.UseCasesImpl
import com.nab.weatherforecast.validSearchResult
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class UseCasesTest {
    @Mock
    private lateinit var repo: WeatherForecastRepository

    @InjectMocks
    private lateinit var useCases: UseCasesImpl

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `check success search data from repo`() {
        dispatcher.runBlockingTest {
            val keyword = VALID_SEARCH
            val time = 100L
            `when`(
                repo.fetchDailyForecast(
                    keyword,
                    time
                )
            ).thenReturn(
                flow { emit(validSearchResult().right()) }
            )
            val either = useCases.getDailyForecast(keyword, time).first()
            assertTrue(either is Either.Right)
            with((either as Either.Right).right.first()) {
                assertEquals(humidity, 85)
                assertEquals(averageTemperature, 30)
                assertEquals(pressure, 1001)
                assertEquals(timestampInSeconds, 1630773376)
                assertEquals(description, "Nothing special")
                assertEquals(temperatureSign, "\u2103")
            }
        }
    }

    @Test
    fun `check unknown error on searching data from repo`() {
        dispatcher.runBlockingTest {
            val keyword = VALID_SEARCH
            val time = 100L
            `when`(repo.fetchDailyForecast(keyword, time)).thenReturn(flow {
                emit(Error.unknown().left())
            })
            val either = useCases.getDailyForecast(keyword, time).first()
            assertTrue(either is Either.Left)
            with(either as Either.Left) {
                assertEquals(Error.UNKNOWN, left.code)
            }
        }
    }
}