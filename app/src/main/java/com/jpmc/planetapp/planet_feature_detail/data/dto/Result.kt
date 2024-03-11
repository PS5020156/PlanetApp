package com.jpmc.planetapp.planet_feature_detail.data.dto

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("__v")
    val v: Int,
    @SerializedName("_id")
    val id: String,
    val description: String,
    val properties: Properties,
    val uid: String
)