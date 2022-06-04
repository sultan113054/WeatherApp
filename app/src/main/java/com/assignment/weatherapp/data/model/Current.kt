package com.assignment.weatherapp.data.model


import com.google.gson.annotations.SerializedName

data class Current(
    @SerializedName("cloud")
    var cloud: Int,
    @SerializedName("condition")
    var condition: Condition,
    @SerializedName("feelslike_c")
    var feelslikeC: Double,
    @SerializedName("feelslike_f")
    var feelslikeF: Double,
    @SerializedName("gust_kph")
    var gustKph: Double,
    @SerializedName("gust_mph")
    var gustMph: Double,
    @SerializedName("humidity")
    var humidity: Int,
    @SerializedName("is_day")
    var isDay: Int,
    @SerializedName("last_updated")
    var lastUpdated: String,
    @SerializedName("last_updated_epoch")
    var lastUpdatedEpoch: Int,
    @SerializedName("precip_in")
    var precipIn: Double,
    @SerializedName("precip_mm")
    var precipMm: Double,
    @SerializedName("pressure_in")
    var pressureIn: Double,
    @SerializedName("pressure_mb")
    var pressureMb: Double,
    @SerializedName("temp_c")
    var tempC: Double,
    @SerializedName("temp_f")
    var tempF: Double,
    @SerializedName("uv")
    var uv: Double,
    @SerializedName("vis_km")
    var visKm: Double,
    @SerializedName("vis_miles")
    var visMiles: Double,
    @SerializedName("wind_degree")
    var windDegree: Int,
    @SerializedName("wind_dir")
    var windDir: String,
    @SerializedName("wind_kph")
    var windKph: Double,
    @SerializedName("wind_mph")
    var windMph: Double
)