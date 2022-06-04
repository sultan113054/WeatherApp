package com.assignment.weatherapp.data.repository


import com.assignment.weatherapp.data.model.ErrorBody
import com.assignment.weatherapp.data.model.WeatherResponse
import com.assignment.weatherapp.data.repository.dataSource.WeatherRemoteDataSource
import com.assignment.weatherapp.data.util.Resource
import com.assignment.weatherapp.domain.repository.WeatherRepository
import com.google.gson.Gson
import retrofit2.Response

class WeatherRepositoryImpl(
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {

    override suspend fun getSearchedLocation(
        searchQuery: String
    ): Resource<WeatherResponse> {
        return responseToResource(
            weatherRemoteDataSource.getSearchedLocation(searchQuery)
        )
    }

    private fun responseToResource(response: Response<WeatherResponse>): Resource<WeatherResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }

        try {
            var errorBody =
                Gson().fromJson(response.errorBody()?.string(), ErrorBody::class.java)
            return Resource.Success(WeatherResponse( errorBody))

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return Resource.Error(response.message())

    }

}