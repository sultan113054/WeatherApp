package com.assignment.weatherapp.presentation.di


import com.assignment.weatherapp.WeatherAPIService
import com.assignment.weatherapp.data.repository.dataSource.WeatherRemoteDataSource
import com.assignment.weatherapp.data.repository.dataSourceImpl.WeatherRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideWeatherRemoteDataSource(
        weatherAPIService: WeatherAPIService
    ): WeatherRemoteDataSource {
        return WeatherRemoteDataSourceImpl(weatherAPIService)
    }

}












