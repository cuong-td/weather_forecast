package com.nab.weatherforecast

import com.nab.weatherforecast.data.remote.response.*
import com.nab.weatherforecast.entity.ForecastInfo

const val VALID_SEARCH = "saigon"
const val INVALID_SEARCH_SHORT = "sa"

fun validSearchResult(size: Int = 7): List<ForecastInfo> = List(size) {
    ForecastInfo(
        1630773376,
        30,
        1001,
        85,
        "Nothing special",
        "\u2103"
    )
}

const val saigonForecastRawData =
    "{\"city\":{\"id\":1580578,\"name\":\"Ho Chi Minh City\",\"coord\":{\"lon\":106.6667,\"lat\":10.8333},\"country\":\"VN\",\"population\":0,\"timezone\":25200},\"cod\":\"200\",\"message\":0.0672792,\"cnt\":7,\"list\":[{\"dt\":1630814400,\"sunrise\":1630795419,\"sunset\":1630839660,\"temp\":{\"day\":299.1,\"min\":296.62,\"max\":301.39,\"night\":298.06,\"eve\":299.38,\"morn\":296.62},\"feels_like\":{\"day\":299.1,\"night\":299.01,\"eve\":299.38,\"morn\":297.5},\"pressure\":1010,\"humidity\":84,\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10d\"}],\"speed\":3.22,\"deg\":349,\"gust\":4.58,\"clouds\":100,\"pop\":0.96,\"rain\":19.05},{\"dt\":1630900800,\"sunrise\":1630881816,\"sunset\":1630926022,\"temp\":{\"day\":302.89,\"min\":297.38,\"max\":303.32,\"night\":299.04,\"eve\":302.33,\"morn\":297.4},\"feels_like\":{\"day\":307.12,\"night\":299.9,\"eve\":307.29,\"morn\":298.33},\"pressure\":1010,\"humidity\":68,\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10d\"}],\"speed\":2.68,\"deg\":297,\"gust\":3.26,\"clouds\":100,\"pop\":0.96,\"rain\":6.29},{\"dt\":1630987200,\"sunrise\":1630968212,\"sunset\":1631012383,\"temp\":{\"day\":303.38,\"min\":297.75,\"max\":303.84,\"night\":297.75,\"eve\":301.74,\"morn\":298},\"feels_like\":{\"day\":308.01,\"night\":298.69,\"eve\":306.21,\"morn\":298.89},\"pressure\":1009,\"humidity\":67,\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10d\"}],\"speed\":3.32,\"deg\":216,\"gust\":7.41,\"clouds\":100,\"pop\":0.97,\"rain\":24.4},{\"dt\":1631073600,\"sunrise\":1631054609,\"sunset\":1631098745,\"temp\":{\"day\":299.62,\"min\":297.32,\"max\":300.24,\"night\":297.66,\"eve\":298.79,\"morn\":297.32},\"feels_like\":{\"day\":299.62,\"night\":298.62,\"eve\":299.76,\"morn\":298.3},\"pressure\":1009,\"humidity\":85,\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10d\"}],\"speed\":2.63,\"deg\":156,\"gust\":5.86,\"clouds\":100,\"pop\":1,\"rain\":23.45},{\"dt\":1631160000,\"sunrise\":1631141005,\"sunset\":1631185106,\"temp\":{\"day\":301.58,\"min\":297.16,\"max\":301.94,\"night\":297.8,\"eve\":301.02,\"morn\":297.16},\"feels_like\":{\"day\":305.65,\"night\":298.75,\"eve\":304.93,\"morn\":298.04},\"pressure\":1010,\"humidity\":76,\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10d\"}],\"speed\":4.35,\"deg\":243,\"gust\":7.38,\"clouds\":78,\"pop\":1,\"rain\":24.41},{\"dt\":1631246400,\"sunrise\":1631227402,\"sunset\":1631271467,\"temp\":{\"day\":301.05,\"min\":297.29,\"max\":301.05,\"night\":298.46,\"eve\":299.72,\"morn\":297.29},\"feels_like\":{\"day\":304.57,\"night\":299.45,\"eve\":299.72,\"morn\":298.19},\"pressure\":1011,\"humidity\":77,\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10d\"}],\"speed\":2.7,\"deg\":284,\"gust\":4.54,\"clouds\":92,\"pop\":1,\"rain\":13.21},{\"dt\":1631332800,\"sunrise\":1631313798,\"sunset\":1631357828,\"temp\":{\"day\":302.6,\"min\":297.6,\"max\":303.39,\"night\":297.6,\"eve\":302.37,\"morn\":297.7},\"feels_like\":{\"day\":306.89,\"night\":298.5,\"eve\":307.39,\"morn\":298.61},\"pressure\":1011,\"humidity\":70,\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"moderate rain\",\"icon\":\"10d\"}],\"speed\":4.62,\"deg\":285,\"gust\":8.54,\"clouds\":100,\"pop\":1,\"rain\":9.56}]}"


fun getWeatherResp() = WeatherForecastResp(
    city = City(
        id = 1L,
        coord = null,
        country = null,
        population = null,
        timezone = null,
        name = "Saigon"
    ),
    count = null,
    code = null,
    message = null,
    list = listForecast()
)

fun listForecast() = List(7) {
    Forecast(
        clouds = null,
        deg = null,
        dt = 1000L,
        feels_like = null,
        gust = null,
        humidity = 1001,
        pop = null,
        pressure = 85,
        rain = null,
        speed = null,
        sunrise = null,
        sunset = null,
        temp = Temp(
            day = null,
            eve = null,
            max = 30.0,
            min = 28.0,
            morn = null,
            night = null
        ),
        weather = listOf(
            Weather(
                description = "Nothing here",
                icon = null,
                id = null,
                main = null
            )
        )
    )
}