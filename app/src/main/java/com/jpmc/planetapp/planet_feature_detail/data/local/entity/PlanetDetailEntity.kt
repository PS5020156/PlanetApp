package com.jpmc.planetapp.planet_feature_detail.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "planet_detail")
data class PlanetDetailEntity(
    val id: String,
    val name: String,
    val orbitalPeriod: String,
    val population: String,
    val rotationPeriod: String,
    val terrain: String,
    @PrimaryKey(autoGenerate = false)
    val url: String
)