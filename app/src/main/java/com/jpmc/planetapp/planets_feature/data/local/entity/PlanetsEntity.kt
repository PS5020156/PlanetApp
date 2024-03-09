package com.jpmc.planetapp.planets_feature.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "planets_table")
data class PlanetsEntity(
    val name: String,
    @PrimaryKey(autoGenerate = false)
    val uid: String,
    val url: String
)
