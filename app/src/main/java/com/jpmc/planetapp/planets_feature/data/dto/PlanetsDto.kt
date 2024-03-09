package com.jpmc.planetapp.planets_feature.data.dto

import com.google.gson.annotations.SerializedName

data class PlanetsDto(
    val message: String,
    val next: String,
    val previous: String? = null,
    val results: List<ResultDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_records")
    val totalRecords: Int
)