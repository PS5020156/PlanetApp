package com.jpmc.planetapp.planets_feature.utils

import com.jpmc.planetapp.planet_feature_detail.data.local.entity.PlanetDetailEntity
import com.jpmc.planetapp.planet_feature_detail.domain.model.PlanetDetail
import com.jpmc.planetapp.planets_feature.data.local.entity.PlanetsEntity

object TestConstant {
    val planetsEntity = PlanetsEntity("Tatooine", "1", "https://www.swapi.tech/api/planets/1")

    const val ID = "1"
    const val NAME = "Tatooine"

    val planetDetailEntity = PlanetDetailEntity(
        "1",
        "Tatooine",
        "304",
        "200000",
        "23",
        "desert",
        "https://www.swapi.tech/api/planets/1"
    )


    val planetDetail = PlanetDetail(
        "1",
        "Tatooine",
        "304",
        "200000",
        "23",
        "desert",
        "https://www.swapi.tech/api/planets/1"
    )
}