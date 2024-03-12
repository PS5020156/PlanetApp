package com.jpmc.planetapp.utils

import com.jpmc.planetapp.planet_feature_detail.data.dto.PlanetDetailDto
import com.jpmc.planetapp.planet_feature_detail.data.dto.Properties
import com.jpmc.planetapp.planet_feature_detail.data.dto.Result
import com.jpmc.planetapp.planet_feature_detail.data.local.entity.PlanetDetailEntity
import com.jpmc.planetapp.planet_feature_detail.domain.model.PlanetDetail
import com.jpmc.planetapp.planets_feature.data.dto.ResultDto
import com.jpmc.planetapp.planets_feature.data.local.entity.PlanetsEntity
import com.jpmc.planetapp.planets_feature.domain.model.Planets

object TestConstant {

    val planets = listOf<Planets>(
        Planets("Tatooine", "1", "https://www.swapi.tech/api/planets/1")
    )

    val planetsEntity = PlanetsEntity("Tatooine", "1", "https://www.swapi.tech/api/planets/1")
    val resultDto = ResultDto("Tatooine", "1", "https://www.swapi.tech/api/planets/1")

    const val NAME = "Tatooine"

    const val ID = "1"

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

    val planetDetailDto = PlanetDetailDto(
        "",
        Result(
            0, "1", "A planet.", Properties(
                "10465",
                "2024-03-11T08:38:48.936Z",
                "arid",
                "1 standard",
                "1",
                "Tatooine",
                "304",
                "200000",
                "23",
                "2024-03-11T08:38:48.936Z",
                "desert",
                "https://www.swapi.tech/api/planets/1"
            ), "1"
        )
    )


}