package com.jpmc.planetapp.planets_feature.presentation.planet_list.state

import com.jpmc.planetapp.planets_feature.domain.model.Planets

data class PlanetsState(
    val data: List<Planets> = emptyList(),
    val isLoading: Boolean = false,
    val errorMsg: String? = null
)