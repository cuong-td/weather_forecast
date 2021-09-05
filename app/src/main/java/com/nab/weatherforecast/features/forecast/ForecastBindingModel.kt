package com.nab.weatherforecast.features.forecast

import android.content.Context
import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.nab.weatherforecast.R
import com.nab.weatherforecast.entity.Either

class ForecastBindingModel(private val context: Context) {
    val errorVisibility = ObservableInt(View.VISIBLE)
    val errorMessage = ObservableField(context.getString(R.string.no_data))
    val loadingVisibility = ObservableInt(View.GONE)
    val dataVisibility = ObservableInt(View.GONE)

    val keyword = ObservableField("")

    fun bind(state: ForecastState) {
        when (state) {
            ForecastState.LoadingState -> {
                loadingVisibility.set(View.VISIBLE)
                errorVisibility.set(View.GONE)
                dataVisibility.set(View.GONE)
            }
            is ForecastState.ErrorState -> {
                loadingVisibility.set(View.GONE)
                errorVisibility.set(View.VISIBLE)
                dataVisibility.set(View.GONE)
                errorMessage.set(
                    when (state.either) {
                        is Either.Left -> context.getString(state.either.left)
                        is Either.Right -> state.either.right
                        else -> context.getString(R.string.err_unknown)
                    }
                )
            }
            is ForecastState.SuccessState -> {
                loadingVisibility.set(View.GONE)
                errorVisibility.set(View.GONE)
                dataVisibility.set(View.VISIBLE)
            }
        }
    }
}