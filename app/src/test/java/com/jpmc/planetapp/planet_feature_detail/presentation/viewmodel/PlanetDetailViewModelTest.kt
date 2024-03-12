package com.jpmc.planetapp.planet_feature_detail.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.jpmc.planetapp.core.common.Result
import com.jpmc.planetapp.planet_feature_detail.domain.usecase.GetPlanetDetailUseCase
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
class PlanetDetailViewModelTest {

    @get: Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var planetDetailUseCase: GetPlanetDetailUseCase

    @Mock
    private lateinit var savedStateHandle: SavedStateHandle

    private lateinit var planetDetailViewModel: PlanetDetailViewModel

    @Before
    fun setUp() {
        planetDetailViewModel = PlanetDetailViewModel(planetDetailUseCase, savedStateHandle)
    }


    @Test
    fun `get planet detail should return success with planetDetails`() = runTest {
        whenever(planetDetailUseCase(TestConstant.ID)).thenReturn(
            flow {
                emit(Result.Success(TestConstant.planetDetail))
            }
        )

        planetDetailViewModel.getPlanetDetail(TestConstant.ID)
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        planetDetailViewModel.planetDetailState.test {
            val result = awaitItem()
            assertEquals(TestConstant.planetDetail.id, result.planetDetail.id)
        }
    }

    @Test
    fun `get planet detail should return failure`() = runTest {
        whenever(planetDetailUseCase(TestConstant.ID)).thenReturn(
            flow {
                emit(Result.Failure("error"))
            }
        )

        planetDetailViewModel.getPlanetDetail(TestConstant.ID)
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        planetDetailViewModel.planetDetailState.test {
            val result = awaitItem()
            assertEquals("error", result.errorMsg)
        }
    }

    @Test
    fun `get planet detail should return loading status`() = runTest {
        whenever(planetDetailUseCase(TestConstant.ID)).thenReturn(
            flow {
                emit(Result.Loading())
            }
        )

        planetDetailViewModel.getPlanetDetail(TestConstant.ID)
        mainCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()
        planetDetailViewModel.planetDetailState.test {
            val result = awaitItem()
            assertEquals(true, result.isLoading)
        }
    }


    @After
    fun tearDown() {
    }
}