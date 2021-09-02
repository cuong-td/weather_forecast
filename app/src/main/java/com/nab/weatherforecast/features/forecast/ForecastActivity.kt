package com.nab.weatherforecast.features.forecast

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.nab.weatherforecast.R
import com.nab.weatherforecast.databinding.ActivityForecastBinding

class ForecastActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityForecastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_forecast)
    }
}