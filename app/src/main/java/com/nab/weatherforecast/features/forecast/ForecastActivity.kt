package com.nab.weatherforecast.features.forecast

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import com.nab.weatherforecast.R
import com.nab.weatherforecast.databinding.ActivityForecastBinding

class ForecastActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityForecastBinding
    private val viewModel by viewModels<ForecastViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_forecast)
        setupViewBinding(viewBinding)
        setupObservables()
        viewModel.test()
    }

    private fun setupViewBinding(viewBinding: ActivityForecastBinding) {
        viewBinding.bindingModel = ForecastBindingModel()
        viewBinding.rvForecast.adapter = ForecastAdapter()
        viewBinding.rvForecast.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun setupObservables() {
        viewModel.state.observe(this) { state ->
            viewBinding.bindingModel?.bind(state)
            (state as? ForecastState.SuccessState)?.let {
                (viewBinding.rvForecast.adapter as? ForecastAdapter)?.submitList(it.data)
            }
        }
    }
}