package com.assignment.weatherapp.presentation.di

import com.assignment.weatherapp.data.repository.WeatherRepositoryImpl
import com.assignment.weatherapp.data.repository.dataSource.WeatherRemoteDataSource
import com.assignment.weatherapp.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideWeatherRepository(
        weatherRemoteDataSource: WeatherRemoteDataSource
    ): WeatherRepository {
        return WeatherRepositoryImpl(weatherRemoteDataSource)
    }

}














