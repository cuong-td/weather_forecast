package com.nab.weatherforecast.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.nab.weatherforecast.App
import com.nab.weatherforecast.DiDelegateTest
import com.nab.weatherforecast.R
import com.nab.weatherforecast.features.forecast.ForecastActivity
import com.nab.weatherforecast.features.forecast.Holder
import com.nab.weatherforecast.usecase.usecases.UseCases
import com.nab.weatherforecast.usecases.FakeUseCases
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class ForecastUiTest {
    private lateinit var scenario: ActivityScenario<ForecastActivity>

    private val useCases: UseCases = FakeUseCases()

    @Before
    fun setup() {
        val app: App =
            InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as App
        app.diDelegate = DiDelegateTest(app, useCases)
        scenario = ActivityScenario.launch(ForecastActivity::class.java)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.nab.weatherforecast", appContext.packageName)
    }

    @Test
    fun searchWithInvalidKeywords() {
        onView(withId(R.id.et_search)).perform(typeText("sa"), closeSoftKeyboard())
        onView(withId(R.id.btn_get_weather)).perform(click())
        onView(withId(R.id.tv_err))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.err_keyword_len)))
    }

    @Test
    fun searchWithValidKeywords() {
        onView(withId(R.id.et_search)).perform(typeText("sai"), closeSoftKeyboard())
        onView(withId(R.id.btn_get_weather)).perform(click())
        Thread.sleep(300)
        onView(withId(R.id.loading)).check(matches(isDisplayed()))
        Thread.sleep(2000)
        onView(withId(R.id.rv_forecast))
            .check(matches(isDisplayed()))
            .perform(RecyclerViewActions.scrollToPosition<Holder>(5))
        Thread.sleep(500)

        onView(withId(R.id.et_search)).perform(
            clearText(),
            typeText("saigo"),
            pressImeActionButton(),
            closeSoftKeyboard()
        )
        Thread.sleep(2100)
        onView(withId(R.id.tv_err))
            .check(matches(isDisplayed()))
            .check(matches(withText(R.string.err_not_found)))
        Thread.sleep(2000)
    }
}