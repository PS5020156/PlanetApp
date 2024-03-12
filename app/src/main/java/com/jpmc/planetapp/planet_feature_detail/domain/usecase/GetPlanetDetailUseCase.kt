package com.jpmc.planetapp.planet_feature_detail.domain.usecase

import com.jpmc.planetapp.core.common.Result
import com.jpmc.planetapp.planet_feature_detail.domain.model.PlanetDetail
import com.jpmc.planetapp.planet_feature_detail.domain.repository.PlanetDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlanetDetailUseCase @Inject constructor(private val planetDetailRepository: PlanetDetailRepository) {
    operator fun invoke(id: String): Flow<Result<PlanetDetail>> =
        planetDetailRepository.getPlanetDetail(id)
}