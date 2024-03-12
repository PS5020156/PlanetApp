package com.jpmc.planetapp.planets_feature.presentation.planet_list.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.jpmc.planetapp.core.common.Result
import com.jpmc.planetapp.planets_feature.domain.usecase.GetPlanetsUseCase
import com.jpmc.planetapp.utils.MainCoroutineRule
import com.jpmc.planetapp.utils.TestConstant
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PlanetsViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var planetsUseCase: GetPlanetsUseCase

    @Mock
    private lateinit var planetsViewModel: PlanetsViewModel

    @Before
    fun setUp() {
        planetsViewModel = PlanetsViewModel(planetsUseCase)
    }

    @Test
    fun `get planets should return success`() = runBlocking {
        whenever(planetsUseCase()).thenReturn(
            flow {
                emit(Result.Success(TestConstant.planets))
            }
        )
        planetsViewModel.getAllPlanets()
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        planetsViewModel.planetsState.test {
            val result = awaitItem()
            assertEquals(true, result.data.isNotEmpty())
        }
    }


    @Test
    fun `get planets should return success with set value`() = runBlocking {
        whenever(planetsUseCase()).thenReturn(
            flow {
                emit(Result.Success(TestConstant.planets))
            }
        )
        planetsViewModel.getAllPlanets()
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        planetsViewModel.planetsState.test {
            val result = awaitItem()
            assertEquals(TestConstant.planets[0].uid, result.data[0].uid)
            assertEquals(TestConstant.planets[0].name, result.data[0].name)
            assertEquals(TestConstant.planets[0].url, result.data[0].url)
        }
    }

    @Test
    fun `get planets in case of error should return error message`() = runBlocking {
        whenever(planetsUseCase()).thenReturn(
            flow {
                emit(Result.Failure("error"))
            }
        )
        planetsViewModel.getAllPlanets()
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        planetsViewModel.planetsState.test {
            val result = awaitItem()
            assertEquals("error", result.errorMsg)
        }
    }

    @Test
    fun `get planets in case of loading should return loading status`() = runBlocking {
        whenever(planetsUseCase()).thenReturn(
            flow {
                emit(Result.Loading())
            }
        )
        planetsViewModel.getAllPlanets()
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        planetsViewModel.planetsState.test {
            val result = awaitItem()
            assertEquals(true, result.isLoading)
        }
    }

    @After
    fun tearDown() {

    }

}