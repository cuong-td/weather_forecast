package com.nab.weatherforecast.entity

data class Error(
    val code: Int = UNKNOWN,
    val message: String = "Unknown Error",
    val data: Any? = null
) {
    companion object {
        const val UNKNOWN = -1
        const val NOT_FOUND = 404
        const val NETWORK = -2
        fun unknown(): Error = Error()
        fun network(): Error = Error(code = NETWORK)
        fun errorData(code: Int, msg: String, data: Any?): Error = Error(code, msg, data)
    }
}