package com.assignment.weatherapp.data.repository.dataSource

import com.assignment.weatherapp.data.model.WeatherResponse
import retrofit2.Response

interface WeatherRemoteDataSource {
    suspend fun getSearchedLocation( searchQuery:String):Response<WeatherResponse>
}