package com.jpmc.planetapp.planets_feature.data.mapper

import com.jpmc.planetapp.planets_feature.data.dto.ResultDto
import com.jpmc.planetapp.planets_feature.data.local.entity.PlanetsEntity
import com.jpmc.planetapp.planets_feature.domain.model.Planets

fun ResultDto.toPlanetsEntity(): PlanetsEntity {
    return PlanetsEntity(name = name, uid = uid, url = url)
}

fun PlanetsEntity.toDomainPlanets(): Planets {
    return Planets(name = name, uid = uid, url = url)
}
