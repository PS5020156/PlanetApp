package com.jpmc.planetapp.planets_feature.data.api

import com.jpmc.planetapp.planets_feature.data.dto.PlanetsDto
import retrofit2.http.GET

interface PlanetsApi {
    @GET("planets")
    suspend fun getAllPlanets(): PlanetsDto
}