package com.nab.weatherforecast.features.forecast

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
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
import com.scottyab.rootbeer.RootBeer
import javax.inject.Inject

class ForecastActivity : AppCompatActivity() {
    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<ForecastViewModel> { vmFactory }

    private lateinit var viewBinding: ActivityForecastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        checkRoot()
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_forecast)
        (application as? App)?.activityInjector()?.inject(this)
        setupViewBinding(viewBinding)
        setupObservables()
    }

    private fun checkRoot() {
        if (RootBeer(this).isRooted)
            Toast.makeText(this, getString(R.string.err_root_detected), Toast.LENGTH_LONG).show()
    }

    private fun setupViewBinding(viewBinding: ActivityForecastBinding) {
        with(viewBinding) {
            bindingModel = ForecastBindingModel(this@ForecastActivity)
            rvForecast.adapter = ForecastAdapter()
            rvForecast.addItemDecoration(
                DividerItemDecoration(
                    this@ForecastActivity,
                    DividerItemDecoration.VERTICAL
                )
            )

            btnGetWeather.setOnDebounceClick {
                search()
            }

            etSearch.setOnEditorActionListener { _, actionId, _ ->
                var overrideAction: Boolean = false
                when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        search()
                        overrideAction = true
                    }
                }
                overrideAction
            }
        }
    }

    private fun search() {
        viewModel.search(viewBinding.bindingModel?.keyword?.get() ?: "")
        hideKeyboard()
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