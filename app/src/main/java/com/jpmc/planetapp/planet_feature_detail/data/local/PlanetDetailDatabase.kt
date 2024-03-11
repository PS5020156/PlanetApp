package com.jpmc.planetapp.planet_feature_detail.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jpmc.planetapp.planet_feature_detail.data.local.dao.PlanetDetailDao
import com.jpmc.planetapp.planet_feature_detail.data.local.entity.PlanetDetailEntity

@Database(entities = [PlanetDetailEntity::class], version = 1, exportSchema = false)
abstract class PlanetDetailDatabase : RoomDatabase() {
    abstract fun getPlanetDetailDao(): PlanetDetailDao
}