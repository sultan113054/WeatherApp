package com.assignment.weatherapp.data.model


import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("country")
    var country: String,
    @SerializedName("lat")
    var lat: Double,
    @SerializedName("localtime")
    var localtime: String,
    @SerializedName("localtime_epoch")
    var localtimeEpoch: Int,
    @SerializedName("lon")
    var lon: Double,
    @SerializedName("name")
    var name: String,
    @SerializedName("region")
    var region: String,
    @SerializedName("tz_id")
    var tzId: String
)