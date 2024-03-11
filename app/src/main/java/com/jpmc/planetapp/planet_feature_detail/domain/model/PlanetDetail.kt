package com.jpmc.planetapp.planet_feature_detail.domain.model

data class PlanetDetail(
    val id: String? = "",
    val name: String? = "",
    val orbitalPeriod: String? = "",
    val population: String? = "",
    val rotationPeriod: String? = "",
    val terrain: String? = "",
    val url: String? = ""
)