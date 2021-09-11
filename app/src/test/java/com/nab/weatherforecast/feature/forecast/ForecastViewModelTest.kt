package com.nab.weatherforecast.feature.forecast

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.Observer
import com.nab.weatherforecast.INVALID_SEARCH_SHORT
import com.nab.weatherforecast.R
import com.nab.weatherforecast.VALID_SEARCH
import com.nab.weatherforecast.entity.Either
import com.nab.weatherforecast.entity.Error
import com.nab.weatherforecast.entity.left
import com.nab.weatherforecast.entity.right
import com.nab.weatherforecast.ext.currentTimestampForQuery
import com.nab.weatherforecast.features.forecast.ForecastState
import com.nab.weatherforecast.features.forecast.ForecastViewModel
import com.nab.weatherforecast.usecase.usecases.UseCases
import com.nab.weatherforecast.validSearchResult
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class ForecastViewModelTest {
    private val dispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var observerState: Observer<ForecastState>

    @Mock
    private lateinit var useCases: UseCases

    @Captor
    var errCaptor: ArgumentCaptor<ForecastState.ErrorState>? = null

    private lateinit var vm: ForecastViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        ArchTaskExecutor.getInstance()
            .setDelegate(object : TaskExecutor() {
                override fun executeOnDiskIO(runnable: Runnable) = runnable.run()

                override fun postToMainThread(runnable: Runnable) = runnable.run()

                override fun isMainThread(): Boolean = true
            })
        errCaptor = ArgumentCaptor.forClass(ForecastState.ErrorState::class.java)
        vm = ForecastViewModel(useCases, dispatcher)
        vm.state.observeForever(observerState)
    }

    @After
    internal fun tearDown() {
        ArchTaskExecutor.getInstance().setDelegate(null)
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `search with valid keywords`() {
        dispatcher.runBlockingTest {
            val successResult = validSearchResult()
            `when`(useCases.getDailyForecast(VALID_SEARCH, currentTimestampForQuery()))
                .thenReturn(flow {
                    emit(successResult.right())
                })
            vm.search(VALID_SEARCH)
            advanceUntilIdle()
            verify(observerState).onChanged(ForecastState.LoadingState)
            advanceUntilIdle()
            verify(observerState).onChanged(any(ForecastState.SuccessState::class.java))
        }
    }

    @Test
    fun `search with too short keywords`() {
        dispatcher.runBlockingTest {
            vm.search(INVALID_SEARCH_SHORT)
            advanceUntilIdle()
            verify(observerState).onChanged(any(ForecastState.ErrorState::class.java))
            verify(observerState).onChanged(errCaptor?.capture())
            assertTrue(errCaptor?.value?.either is Either.Left)
            assertEquals(R.string.err_keyword_len, (errCaptor?.value?.either as Either.Left).left)
        }
    }

    @Test
    fun `searching has no data`() {
        dispatcher.runBlockingTest {
            `when`(useCases.getDailyForecast(VALID_SEARCH, currentTimestampForQuery()))
                .thenReturn(flow {
                    emit(Error.errorData(Error.NOT_FOUND, "").left())
                })
            vm.search(VALID_SEARCH)
            advanceUntilIdle()
            verify(observerState).onChanged(ForecastState.LoadingState)
            advanceUntilIdle()
            verify(observerState).onChanged(any(ForecastState.ErrorState::class.java))
        }
    }
}