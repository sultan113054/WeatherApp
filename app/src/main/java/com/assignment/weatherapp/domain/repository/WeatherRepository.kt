package com.assignment.weatherapp.domain.repository

import com.assignment.weatherapp.data.model.WeatherResponse
import com.assignment.weatherapp.data.util.Resource


interface WeatherRepository {
    suspend fun getSearchedLocation(searchQuery: String): Resource<WeatherResponse>
}