package com.nab.weatherforecast.features.forecast

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt

class ForecastBindingModel {
    val errorVisibility = ObservableInt(View.GONE)
    val errorMessage = ObservableField<String>()
    val loadingVisibility = ObservableInt(View.GONE)
    val dataVisibility = ObservableInt(View.GONE)


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
                errorMessage.set(state.data)
            }
            is ForecastState.SuccessState -> {
                loadingVisibility.set(View.GONE)
                errorVisibility.set(View.GONE)
                dataVisibility.set(View.VISIBLE)
            }
        }
    }
}