package com.assignment.weatherapp.data.model


import com.google.gson.annotations.SerializedName

data class ErrorBody(
    @SerializedName("error")
    var error: Error
)