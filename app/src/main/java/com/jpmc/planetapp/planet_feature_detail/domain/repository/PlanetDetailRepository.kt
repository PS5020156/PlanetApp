package com.jpmc.planetapp.planet_feature_detail.domain.repository

import com.jpmc.planetapp.core.common.Result
import com.jpmc.planetapp.planet_feature_detail.domain.model.PlanetDetail
import kotlinx.coroutines.flow.Flow

interface PlanetDetailRepository {
    fun getPlanetDetail(id: String): Flow<Result<PlanetDetail>>
}