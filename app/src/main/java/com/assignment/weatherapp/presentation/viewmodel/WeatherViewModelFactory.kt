package com.assignment.weatherapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.assignment.weatherapp.domain.usecase.GetSearchedLocationUseCase


class WeatherViewModelFactory(
    private val app: Application,
    private val getSearchedMoviesUseCase: GetSearchedLocationUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WeatherViewModel(app, getSearchedMoviesUseCase) as T
    }
}









