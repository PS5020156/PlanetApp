package com.jpmc.planetapp.utils

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

}