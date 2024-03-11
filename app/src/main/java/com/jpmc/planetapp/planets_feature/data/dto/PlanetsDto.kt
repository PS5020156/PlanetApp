package com.jpmc.planetapp.planets_feature.data.dto

import com.google.gson.annotations.SerializedName

data class PlanetsDto(
    val message: String? = "",
    val next: String? = "",
    val previous: String? = "",
    val results: List<ResultDto> = emptyList(),
    @SerializedName("total_pages")
    val totalPages: Int? = 0,
    @SerializedName("total_records")
    val totalRecords: Int? = 0
)