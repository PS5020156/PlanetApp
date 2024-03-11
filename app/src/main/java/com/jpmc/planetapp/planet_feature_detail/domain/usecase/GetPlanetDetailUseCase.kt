package com.jpmc.planetapp.planet_feature_detail.domain.usecase

import com.jpmc.planetapp.planet_feature_detail.domain.repository.PlanetDetailRepository
import javax.inject.Inject

class GetPlanetDetailUseCase @Inject constructor(private val planetDetailRepository: PlanetDetailRepository) {
    operator fun invoke(id: String) = planetDetailRepository.getPlanetDetail(id)
}