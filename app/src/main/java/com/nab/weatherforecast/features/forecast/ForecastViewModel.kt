package com.nab.weatherforecast.features.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nab.weatherforecast.base.BaseViewModel
import com.nab.weatherforecast.entity.Either
import com.nab.weatherforecast.entity.ForecastInfo
import com.nab.weatherforecast.framework.UseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

class ForecastViewModel
@Inject
constructor(
    private val useCases: UseCases
) : BaseViewModel() {

    val state: LiveData<ForecastState> = MutableLiveData()
    private var searchJob: Job? = null

//    init {
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://api.openweathermap.org/")
//            .addConverterFactory(GsonConverterFactory.create(Gson()))
//            .build()
//        val source = retrofit.create<RemoteSource>()
//        val getDailyForecast = GetDailyForecast(WeatherForecastRepoImpl(source))
//        useCases = UseCases(getDailyForecast)
//    }

    fun test() {
        state.setData(ForecastState.SuccessState(
            List(Random.nextInt(7, 10)) {
                ForecastInfo(
                    0,
                    "${Random.nextInt(25, 40)}\u2103",
                    "${Random.nextInt(600, 2000)}",
                    "${Random.nextInt(60, 100)}%",
                    "Test is okay"
                )
            }
        ))
    }

    fun search() {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            state.setData(ForecastState.LoadingState)
            withContext(Dispatchers.IO) {
                useCases.getDailyForecast()
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
    }
}