package com.nab.weatherforecast

import com.nab.weatherforecast.entity.ForecastInfo

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