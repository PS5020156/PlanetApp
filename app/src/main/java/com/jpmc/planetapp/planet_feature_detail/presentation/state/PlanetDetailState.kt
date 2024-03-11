package com.jpmc.planetapp.planet_feature_detail.presentation.state

import com.jpmc.planetapp.planet_feature_detail.domain.model.PlanetDetail

data class PlanetDetailState(
    val planetDetail: PlanetDetail = PlanetDetail(),
    val isLoading: Boolean = false,
    val errorMsg: String? = ""
)