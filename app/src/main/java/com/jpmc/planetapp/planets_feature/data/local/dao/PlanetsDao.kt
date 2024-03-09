package com.jpmc.planetapp.planets_feature.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jpmc.planetapp.planets_feature.data.local.entity.PlanetsEntity


@Dao
interface PlanetsDao {
    @Query("SELECT * FROM planets_table")
    suspend fun getPlanets(): List<PlanetsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlanets(planets: List<PlanetsEntity>)

    @Query("Delete FROM planets_table")
    fun deletePlanets()
}