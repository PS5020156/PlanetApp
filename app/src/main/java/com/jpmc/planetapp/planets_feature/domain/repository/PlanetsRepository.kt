package com.jpmc.planetapp.planets_feature.domain.repository

import com.jpmc.planetapp.core.common.Result
import com.jpmc.planetapp.planets_feature.domain.model.Planets
import kotlinx.coroutines.flow.Flow

interface PlanetsRepository {
    fun getAllPlanets(): Flow<Result<List<Planets>>>
}