package com.nab.weatherforecast.features.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nab.weatherforecast.R
import com.nab.weatherforecast.base.BaseViewModel
import com.nab.weatherforecast.entity.Either
import com.nab.weatherforecast.entity.Error
import com.nab.weatherforecast.entity.left
import com.nab.weatherforecast.entity.right
import com.nab.weatherforecast.ext.currentTimestampForQuery
import com.nab.weatherforecast.usecase.UseCases
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ForecastViewModel
@Inject
constructor(
    private val useCases: UseCases,
    private val dispatcher: CoroutineDispatcher
) : BaseViewModel() {

    val state: LiveData<ForecastState> = MutableLiveData()

    private var searchJob: Job? = null

    fun search(keyword: String) {
        searchJob?.cancel()
        if (keyword.length < 3) {
            state.setData(ForecastState.ErrorState(R.string.err_keyword_len.left()))
            return
        }
        searchJob = viewModelScope.launch {
            state.setData(ForecastState.LoadingState)
            withContext(dispatcher) {
                useCases.getDailyForecast(keyword, currentTimestampForQuery())
                    .collect {
                        when (it) {
                            is Either.Left -> {
                                when (it.left.code) {
                                    Error.NOT_FOUND -> state.postData(ForecastState.ErrorState(R.string.err_not_found.left()))
                                    Error.UNKNOWN -> state.postData(ForecastState.ErrorState(R.string.err_unknown.left()))
                                    Error.NETWORK -> state.postData(ForecastState.ErrorState(R.string.err_network.left()))
                                    else -> state.postData(ForecastState.ErrorState(it.left.message.right()))
                                }
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