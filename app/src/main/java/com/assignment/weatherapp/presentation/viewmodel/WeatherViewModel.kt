package com.assignment.weatherapp.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.assignment.weatherapp.data.model.WeatherResponse
import com.assignment.weatherapp.data.util.Resource
import com.assignment.weatherapp.domain.usecase.GetSearchedLocationUseCase
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val app: Application,
    private val getSearchedLocationUseCase: GetSearchedLocationUseCase
) : AndroidViewModel(app) {
    var searchedLocation: MutableLiveData<Resource<WeatherResponse>> = MutableLiveData()
    private var errorMessage = MutableLiveData<String>()
    private var isNavigated = MutableLiveData<Boolean>()

    init {
        isNavigated.value = false
        errorMessage.value = ""

    }

    val getsearchedLocation: LiveData<Resource<WeatherResponse>>
        get() = searchedLocation

    val getErrorMessage: LiveData<String>
        get() = errorMessage

    val getNavigationValue: LiveData<Boolean>
        get() = isNavigated

    fun setNavigationValue(value: Boolean) {
        this.isNavigated.value = value
    }

    fun setErrorMessage(errorMessage: String?) {
        if (!errorMessage.isNullOrBlank()) {
            this.errorMessage.value = errorMessage
        }
    }

    fun searchLocation(searchQuery: String) = viewModelScope.launch {
        searchedLocation.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {
                val response = getSearchedLocationUseCase.execute(
                    searchQuery

                )
                searchedLocation.postValue(response)
            } else {
                searchedLocation.postValue(Resource.Error("No internet connection"))
            }
        } catch (e: Exception) {
            searchedLocation.postValue(Resource.Error(e.message.toString()))
        }
    }

    private fun isNetworkAvailable(context: Context?): Boolean {
        this.isNavigated.value = false
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
        return false

    }
}














