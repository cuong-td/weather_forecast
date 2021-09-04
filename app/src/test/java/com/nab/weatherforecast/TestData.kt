package com.nab.weatherforecast

import com.nab.weatherforecast.entity.ForecastInfo

fun validSearchResult(): List<ForecastInfo> = List(7) {
    ForecastInfo(
        1630773376,
        30,
        1001,
        85,
        "Nothing special",
        "\u1023"
    )
}