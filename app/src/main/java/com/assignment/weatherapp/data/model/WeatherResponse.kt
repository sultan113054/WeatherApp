package com.assignment.weatherapp.data.model


import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("error")
    var errorBody: ErrorBody?=null,
    @SerializedName("current")
    var current: Current?=null,
    @SerializedName("location")
    var location: Location?=null
)