package com.jpmc.planetapp.planets_feature.data.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.jpmc.planetapp.planets_feature.data.local.PlanetsDatabase
import com.jpmc.planetapp.planets_feature.data.local.entity.PlanetsEntity
import com.jpmc.planetapp.planets_feature.utils.TestConstant
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PackageDaoTest {

    private lateinit var planetsDatabase: PlanetsDatabase
    private lateinit var planetsDao: PlanetsDao
    private lateinit var planets: List<PlanetsEntity>

    @Before
    fun setUp() {
        planetsDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PlanetsDatabase::class.java
        ).allowMainThreadQueries().build()
        planetsDao = planetsDatabase.getPlanetsDao()
        planets = listOf(TestConstant.planetsEntity)
    }


    @Test
    fun insertAllPlanets_expectedSingleCocktail() = runTest {
        planetsDao.insertPlanets(planets)
        val actual = planetsDao.getPlanets()
        Assert.assertEquals(1, actual.size)
    }

    @Test
    fun checkPlanets_isPresent_expectedTrue() = runTest {
        planetsDao.insertPlanets(planets)
        val actual = planetsDao.getPlanets()
        Assert.assertEquals("Tatooine", actual[0].name)
    }

    @Test
    fun insertedPlanets_deletedSuccess_expectedTrue() = runTest {
        planetsDao.insertPlanets(planets)
        planetsDao.deletePlanets()
        val actual = planetsDao.getPlanets()
        Assert.assertEquals(0, actual.size)
    }

    @After
    fun tearDown() {
        planetsDatabase.close()
    }
}