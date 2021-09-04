package com.nab.weatherforecast.framework.db.entity

import androidx.room.Entity

@Entity(tableName = "city_query", primaryKeys = ["query"])
data class CityQuery(
    val cityId: Long = 0L,
    val query: String = "",
    val name: String = ""
)