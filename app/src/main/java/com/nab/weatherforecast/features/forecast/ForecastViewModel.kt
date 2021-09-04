package com.nab.weatherforecast.features.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nab.weatherforecast.base.BaseViewModel
import com.nab.weatherforecast.entity.Either
import com.nab.weatherforecast.framework.UseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ForecastViewModel
@Inject
constructor(
    private val useCases: UseCases
) : BaseViewModel() {

    val state: LiveData<ForecastState> = MutableLiveData()

    private var searchJob: Job? = null

    fun search(keyword: String) {
        searchJob?.cancel()
        if (keyword.length < 3) {
            state.setData(ForecastState.ErrorState("Keyword is too short"))
            return
        }
        searchJob = viewModelScope.launch {
            state.setData(ForecastState.LoadingState)
            withContext(Dispatchers.IO) {
                useCases.getDailyForecast(keyword)
                    .collect {
                        when (it) {
                            is Either.Left -> {
                                state.postData(ForecastState.ErrorState("Error data"))
                            }
                            is Either.Right -> {
                                state.postData(ForecastState.SuccessState(it.right))
                            }
                        }
                    }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
    }
}