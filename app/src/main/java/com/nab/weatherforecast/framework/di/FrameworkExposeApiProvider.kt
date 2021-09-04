package com.nab.weatherforecast.framework.di

import com.nab.weatherforecast.data.local.LocalSource

interface FrameworkExposeApiProvider {
    val localSource: LocalSource
}