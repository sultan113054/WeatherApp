package com.assignment.weatherapp.data.model


import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("code")
    var code: Int,
    @SerializedName("message")
    var message: String
)