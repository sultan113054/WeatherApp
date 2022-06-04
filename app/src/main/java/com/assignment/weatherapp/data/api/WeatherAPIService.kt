package com.assignment.weatherapp

import com.assignment.weatherapp.data.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPIService {

    @GET("v1/current.json")
    suspend fun getSearchedLocation(
        @Query(value = "q")
        searchExpression: String,
        @Query(value = "key")
        apiKey: String = BuildConfig.API_KEY,
        @Query(value = "aqi")
        aqi: String = "no",
    ): Response<WeatherResponse>

}