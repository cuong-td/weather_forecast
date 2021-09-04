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