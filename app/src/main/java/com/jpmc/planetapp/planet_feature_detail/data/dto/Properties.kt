package com.jpmc.planetapp.planet_feature_detail.data.dto

import com.google.gson.annotations.SerializedName

data class Properties(
    val climate: String,
    val created: String,
    val diameter: String,
    val edited: String,
    val gravity: String,
    val name: String,
    @SerializedName("orbital_period")
    val orbitalPeriod: String,
    val population: String,
    @SerializedName("rotation_period")
    val rotationPeriod: String,
    @SerializedName("surface_water")
    val surfaceWater: String,
    val terrain: String,
    val url: String
)