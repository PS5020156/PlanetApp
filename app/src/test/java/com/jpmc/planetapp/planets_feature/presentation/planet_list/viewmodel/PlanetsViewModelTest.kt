package com.jpmc.planetapp.planets_feature.presentation.planet_list.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.jpmc.planetapp.core.common.Result
import com.jpmc.planetapp.planets_feature.domain.usecase.GetPlanetsUseCase
import com.jpmc.planetapp.utils.MainCoroutineRule
import com.jpmc.planetapp.utils.TestConstant
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
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
    private lateinit var planetsViewModel: PlanetsViewModel

    @Before
    fun setUp() {
        planetsViewModel = PlanetsViewModel(planetsUseCase)
    }

    @Test
    fun `get planets when no planet return is empty`() = runTest {
        whenever(planetsUseCase()).thenReturn(flow {
            emit(Result.Success(emptyList()))
        })
        planetsViewModel.getAllPlanets()
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        planetsViewModel.planetsState.test {
            val result = awaitItem()
            assertEquals(result.data.size, 0)
            assertEquals(result.isLoading, false)
        }
    }

    @Test
    fun `get planets when planet is non empty`() = runTest {

        whenever(planetsUseCase()).thenReturn(flow {
            emit(Result.Success(TestConstant.planets))
        })
        planetsViewModel.getAllPlanets()
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        planetsViewModel.planetsState.test {
            val result = awaitItem()
            assertEquals(result.data.isEmpty(), false)
            assertEquals(result.data[0].name, TestConstant.NAME)
            assertEquals(result.data[0].uid, "1")
            assertEquals(result.isLoading, false)
        }
    }

    @Test
    fun `get planets in case of error`() = runTest {
        whenever(planetsUseCase()).thenReturn(flow {
            emit(Result.Failure("error"))
        })
        planetsViewModel.getAllPlanets()
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        planetsViewModel.planetsState.test {
            val result = awaitItem()
            assertEquals(result.data.isEmpty(), true)
            assertEquals(result.errorMsg, "error")
            assertEquals(result.isLoading, false)
        }
    }

    @After
    fun tearDown() {

    }

}