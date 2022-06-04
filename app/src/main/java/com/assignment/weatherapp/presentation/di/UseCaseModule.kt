package com.assignment.weatherapp.presentation.di


import com.assignment.weatherapp.domain.repository.WeatherRepository
import com.assignment.weatherapp.domain.usecase.GetSearchedLocationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetSearchLocationUseCase(
        weatherRepository: WeatherRepository
    ): GetSearchedLocationUseCase {
        return GetSearchedLocationUseCase(weatherRepository)
    }
}


















