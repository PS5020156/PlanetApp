package com.jpmc.planetapp.planets_feature.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jpmc.planetapp.planets_feature.data.local.dao.PlanetsDao
import com.jpmc.planetapp.planets_feature.data.local.entity.PlanetsEntity

@Database(entities = [PlanetsEntity::class], version = 1, exportSchema = false)
abstract class PlanetsDatabase : RoomDatabase() {
    abstract fun getPlanetsDao(): PlanetsDao
}