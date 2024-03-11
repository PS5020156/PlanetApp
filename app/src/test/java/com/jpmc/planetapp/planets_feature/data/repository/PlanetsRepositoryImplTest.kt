package com.jpmc.planetapp.planets_feature.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.jpmc.planetapp.core.common.Result
import com.jpmc.planetapp.planets_feature.data.api.PlanetsApi
import com.jpmc.planetapp.planets_feature.data.dto.PlanetsDto
import com.jpmc.planetapp.planets_feature.data.dto.ResultDto
import com.jpmc.planetapp.planets_feature.data.local.dao.PlanetsDao
import com.jpmc.planetapp.planets_feature.domain.repository.PlanetsRepository
import com.jpmc.planetapp.utils.MainCoroutineRule
import com.jpmc.planetapp.utils.TestConstant
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PlanetsRepositoryImplTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var planetsApi: PlanetsApi

    @Mock
    private lateinit var planetsDao: PlanetsDao

    @Mock
    private lateinit var planetsRepository: PlanetsRepository

    @Before
    fun setUp() {
        planetsRepository =
            PlanetsRepositoryImpl(planetsApi, planetsDao, mainCoroutineRule.testDispatcher)
    }

    @Test
    fun `planets list when there is internet and data were inserted should return success`() =
        runTest {
            whenever(planetsDao.getPlanets()).thenReturn(listOf())
            whenever(planetsApi.getAllPlanets()).thenReturn(PlanetsDto())
            planetsRepository.getAllPlanets().test {
                awaitItem()
                awaitItem()
                val actual = awaitItem()
                cancelAndIgnoreRemainingEvents()
                Assert.assertTrue(actual is Result.Success)
            }
        }

    @Test
    fun `planets db empty fetches data first time from server`() = runTest {
        whenever(planetsDao.getPlanets()).thenReturn(listOf())
        whenever(planetsApi.getAllPlanets()).thenReturn(PlanetsDto())
        val actual = planetsRepository.getAllPlanets().first()
        Assert.assertEquals(true, actual.data.isNullOrEmpty())
        Assert.assertTrue(actual is Result.Loading)
    }

    @Test
    fun `throws generic exception should return failure`() = runTest {
        whenever(planetsDao.getPlanets()).thenReturn(listOf(TestConstant.planetsEntity))
        whenever(planetsApi.getAllPlanets()).thenThrow(RuntimeException::class.java)
        planetsRepository.getAllPlanets().test {
            awaitItem()
            awaitItem()
            val response = awaitItem()
            cancelAndIgnoreRemainingEvents()
            Assert.assertTrue(response is Result.Failure)
        }
    }

    @Test
    fun `data emitted from table successfully before network call`() = runTest {
        whenever(planetsDao.getPlanets()).thenReturn(listOf(TestConstant.planetsEntity))
        planetsRepository.getAllPlanets().test {
            awaitItem()
            val response = awaitItem()
            cancelAndIgnoreRemainingEvents()
            Assert.assertEquals(1, response.data?.size)
        }
    }

    @Test
    fun `data present is db and network return empty response should return success`() = runTest {
        whenever(planetsDao.getPlanets()).thenReturn(listOf(TestConstant.planetsEntity))
        whenever(planetsApi.getAllPlanets()).thenReturn(PlanetsDto(results = listOf<ResultDto>()))
        planetsRepository.getAllPlanets().test {
            awaitItem()
            awaitItem()
            val response = awaitItem()
            cancelAndIgnoreRemainingEvents()
            Assert.assertTrue(response is Result.Success)
        }
    }

    @Test
    fun `data present in db and network return valid response should return success with valid data`() =
        runTest {
            whenever(planetsDao.getPlanets()).thenReturn(listOf(TestConstant.planetsEntity))
            whenever(planetsApi.getAllPlanets()).thenReturn(
                PlanetsDto(
                    results = listOf<ResultDto>(
                        TestConstant.resultDto
                    )
                )
            )
            planetsRepository.getAllPlanets().test {
                awaitItem()
                awaitItem()
                val response = awaitItem()
                cancelAndIgnoreRemainingEvents()
                Assert.assertTrue(response is Result.Success)
                Assert.assertEquals(true, response.data?.isNotEmpty())
                Assert.assertEquals(TestConstant.NAME, response.data?.get(0)?.name)
            }
        }

    @Test
    fun `data present in db and network return valid response should refresh data`() = runTest {
        whenever(planetsDao.getPlanets()).thenReturn(listOf(TestConstant.planetsEntity))
        whenever(planetsApi.getAllPlanets()).thenReturn(
            PlanetsDto(
                results = listOf<ResultDto>(
                    TestConstant.resultDto
                )
            )
        )
        planetsRepository.getAllPlanets().test {
            val loadingEmitted = awaitItem()
            Assert.assertTrue(loadingEmitted is Result.Loading)
            val response = awaitItem()
            cancelAndIgnoreRemainingEvents()
            Assert.assertTrue(response is Result.Success)
            Assert.assertEquals(true, response.data?.isNotEmpty())
            Assert.assertEquals(TestConstant.NAME, response.data?.get(0)?.name)
        }
    }

    @After
    fun tearDown() {

    }
}