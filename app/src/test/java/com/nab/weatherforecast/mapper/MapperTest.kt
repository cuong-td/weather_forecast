package com.nab.weatherforecast.mapper

import com.nab.weatherforecast.data.mapper.toInfo
import com.nab.weatherforecast.data.mapper.toLocalError
import com.nab.weatherforecast.data.remote.request.TemperatureUnit
import com.nab.weatherforecast.entity.Error
import com.nab.weatherforecast.listForecast
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException
import java.net.UnknownHostException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class MapperTest {
    @Test
    fun `forecast map to info`() {
        val forecast = listForecast().first()
        val info = forecast.toInfo(TemperatureUnit.CELSIUS)
        assertEquals(1000, info.timestampInSeconds)
        assertEquals(29, info.averageTemperature)
        assertEquals(85, info.pressure)
        assertEquals(1001, info.humidity)
        assertEquals("Nothing here", info.description)
        assertEquals("\u2103", info.temperatureSign)
    }

    @Test
    fun `throwable to local error`() {
        var err = OutOfMemoryError().toLocalError()
        assertEquals(Error.UNKNOWN, err.code)

        err = UnknownHostException().toLocalError()
        assertEquals(Error.NETWORK, err.code)

        err = IOException().toLocalError()
        assertEquals(Error.NETWORK, err.code)
    }
}