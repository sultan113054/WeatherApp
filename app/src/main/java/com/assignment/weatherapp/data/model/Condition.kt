package com.assignment.weatherapp.data.model


import com.google.gson.annotations.SerializedName

data class Condition(
    @SerializedName("code")
    var code: Int,
    @SerializedName("icon")
    var icon: String,
    @SerializedName("text")
    var text: String
)