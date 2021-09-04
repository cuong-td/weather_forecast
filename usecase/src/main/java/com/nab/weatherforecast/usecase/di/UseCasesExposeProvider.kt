package com.nab.weatherforecast.usecase.di

import com.nab.weatherforecast.usecase.usecases.UseCases


interface UseCasesExposeProvider {
    val useCases: UseCases
}