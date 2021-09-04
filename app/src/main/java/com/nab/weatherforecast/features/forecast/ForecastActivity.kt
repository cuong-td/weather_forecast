package com.nab.weatherforecast.features.forecast

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.nab.weatherforecast.App
import com.nab.weatherforecast.R
import com.nab.weatherforecast.databinding.ActivityForecastBinding
import com.nab.weatherforecast.ext.hideKeyboard
import com.nab.weatherforecast.ext.setOnDebounceClick
import javax.inject.Inject

class ForecastActivity : AppCompatActivity() {
    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<ForecastViewModel> { vmFactory }

    private lateinit var viewBinding: ActivityForecastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_forecast)
        (application as? App)?.activityInjector()?.inject(this)
        setupViewBinding(viewBinding)
        setupObservables()
    }

    private fun setupViewBinding(viewBinding: ActivityForecastBinding) {
        with(viewBinding) {
            bindingModel = ForecastBindingModel()
            rvForecast.adapter = ForecastAdapter()
            rvForecast.addItemDecoration(
                DividerItemDecoration(
                    this@ForecastActivity,
                    DividerItemDecoration.VERTICAL
                )
            )

            btnGetWeather.setOnDebounceClick {
                viewModel.search(viewBinding.bindingModel?.keyword?.get() ?: "")
                hideKeyboard()
            }
        }
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