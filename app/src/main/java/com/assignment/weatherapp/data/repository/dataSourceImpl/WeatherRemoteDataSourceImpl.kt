package com.assignment.weatherapp.data.repository.dataSourceImpl

import com.assignment.weatherapp.WeatherAPIService
import com.assignment.weatherapp.data.model.WeatherResponse
import com.assignment.weatherapp.data.repository.dataSource.WeatherRemoteDataSource

import retrofit2.Response


class WeatherRemoteDataSourceImpl(
    private val weatherAPIService: WeatherAPIService
) : WeatherRemoteDataSource {


    override suspend fun getSearchedLocation(
        searchQuery: String,
    ): Response<WeatherResponse> {
        return weatherAPIService.getSearchedLocation(searchQuery)
    }


}