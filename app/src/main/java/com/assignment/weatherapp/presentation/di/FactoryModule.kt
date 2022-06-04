package com.assignment.weatherapp.presentation.di

import android.app.Application
import com.assignment.weatherapp.domain.usecase.GetSearchedLocationUseCase
import com.assignment.weatherapp.presentation.viewmodel.WeatherViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Singleton
    @Provides
    fun provideWeatherViewModelFactory(
        application: Application, getSearchedLocationUseCase: GetSearchedLocationUseCase
    ): WeatherViewModelFactory {
        return WeatherViewModelFactory(
            application,
            getSearchedLocationUseCase
        )
    }

}








