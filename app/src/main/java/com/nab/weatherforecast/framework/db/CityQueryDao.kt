package com.nab.weatherforecast.framework.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nab.weatherforecast.framework.db.entity.CityQuery
import com.nab.weatherforecast.framework.db.entity.Forecast

@Dao
interface CityQueryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeCityQuery(cityQuery: CityQuery)

    @Query("delete from city_query where timestamp < :timestamp")
    suspend fun deleteOldQuery(timestamp: Long)
}