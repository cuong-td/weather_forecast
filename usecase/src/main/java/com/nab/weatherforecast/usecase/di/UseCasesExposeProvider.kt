package com.nab.weatherforecast.usecase.di

import com.nab.weatherforecast.usecase.UseCases

interface UseCasesExposeProvider {
    val useCases: UseCases
}