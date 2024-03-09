package com.jpmc.planetapp.planets_feature.domain.usecase

import com.jpmc.planetapp.planets_feature.domain.repository.PlanetsRepository
import javax.inject.Inject

class GetPlanetsUseCase @Inject constructor(private val planetsRepository: PlanetsRepository) {
    operator fun invoke() = planetsRepository.getAllPlanets()
}