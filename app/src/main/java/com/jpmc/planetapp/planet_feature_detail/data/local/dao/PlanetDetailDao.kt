package com.jpmc.planetapp.planet_feature_detail.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jpmc.planetapp.planet_feature_detail.data.local.entity.PlanetDetailEntity

@Dao
interface PlanetDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlanetDetail(planetDetailEntity: PlanetDetailEntity)

    @Query("delete from planet_detail")
    fun deletePlanetDetails()

    @Query("select * from planet_detail where id = :id")
    suspend fun getPlanetDetail(id: String): PlanetDetailEntity

}