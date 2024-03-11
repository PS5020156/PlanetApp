package com.jpmc.planetapp.planet_feature_detail.data.api

import com.jpmc.planetapp.planet_feature_detail.data.dto.PlanetDetailDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PlanetDetailApi {
    @GET("planets/{id}")
    suspend fun getPlanetDetails(@Path("id") id: String): PlanetDetailDto
}