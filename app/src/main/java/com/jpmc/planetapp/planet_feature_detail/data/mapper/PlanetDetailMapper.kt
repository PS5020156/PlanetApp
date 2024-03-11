package com.jpmc.planetapp.planet_feature_detail.data.mapper

import com.jpmc.planetapp.planet_feature_detail.data.dto.Result
import com.jpmc.planetapp.planet_feature_detail.data.local.entity.PlanetDetailEntity
import com.jpmc.planetapp.planet_feature_detail.domain.model.PlanetDetail


fun PlanetDetailEntity.toDomainPlanetDetail(): PlanetDetail {
    return PlanetDetail(id, name, orbitalPeriod, population, rotationPeriod, terrain, url)
}

fun Result.toPlanetDetailEntity(): PlanetDetailEntity {
    return PlanetDetailEntity(
        id = uid,
        name = properties.name,
        orbitalPeriod = properties.orbitalPeriod,
        population = properties.population,
        rotationPeriod = properties.rotationPeriod,
        terrain = properties.terrain,
        url = properties.url
    )
}