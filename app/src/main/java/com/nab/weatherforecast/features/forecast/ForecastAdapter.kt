package com.nab.weatherforecast.features.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nab.weatherforecast.data.ForecastInfo
import com.nab.weatherforecast.databinding.ItemWeatherBinding

class ForecastAdapter : ListAdapter<ForecastInfo, Holder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val viewBinding = ItemWeatherBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).apply {
            bindingModel = ForecastItemBindingModel()
        }
        return Holder(viewBinding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindData(getItem(position))
    }
}

class Holder(private val viewBinding: ItemWeatherBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {
    fun bindData(data: ForecastInfo) {
        viewBinding.bindingModel?.bind(data)
//        viewBinding.executePendingBindings()
    }
}

private class DiffCallback : DiffUtil.ItemCallback<ForecastInfo>() {
    override fun areItemsTheSame(oldItem: ForecastInfo, newItem: ForecastInfo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ForecastInfo, newItem: ForecastInfo): Boolean {
        return oldItem == newItem
    }
}
