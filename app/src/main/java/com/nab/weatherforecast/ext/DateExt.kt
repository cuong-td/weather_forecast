package com.nab.weatherforecast.ext

import android.text.format.DateFormat
import java.util.*

const val DATE_DISPLAY_FORMAT = "EEE, dd MMM yyyy"

fun Date.display(pattern: String = DATE_DISPLAY_FORMAT): String =
    DateFormat.format(pattern, this).toString()

fun displayTimestamp(
    timestamp: Long = Calendar.getInstance().timeInMillis,
    pattern: String = DATE_DISPLAY_FORMAT
): String =
    DateFormat.format(pattern, timestamp).toString()

fun currentTimestampForQuery(): Long {
    val now = Calendar.getInstance()
    return with(now) {
        val day = get(Calendar.DAY_OF_MONTH)
        val month = get(Calendar.MONTH)
        val year = get(Calendar.YEAR)
        clear()
        set(Calendar.DAY_OF_MONTH, day)
        set(Calendar.MONTH, month)
        set(Calendar.YEAR, year)
        timeInMillis
    }
}