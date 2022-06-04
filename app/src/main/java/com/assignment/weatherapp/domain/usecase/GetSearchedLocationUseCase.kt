package com.assignment.weatherapp.domain.usecase

import com.assignment.weatherapp.data.model.WeatherResponse
import com.assignment.weatherapp.data.util.Resource
import com.assignment.weatherapp.domain.repository.WeatherRepository


class GetSearchedLocationUseCase(private val weatherRepository: WeatherRepository) {
    suspend fun execute(searchQuery: String): Resource<WeatherResponse> {
        return weatherRepository.getSearchedLocation(searchQuery)
    }
}