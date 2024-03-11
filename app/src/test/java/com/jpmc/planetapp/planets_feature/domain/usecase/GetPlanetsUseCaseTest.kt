package com.jpmc.planetapp.planets_feature.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.jpmc.planetapp.core.common.Result
import com.jpmc.planetapp.planets_feature.domain.repository.PlanetsRepository
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
class GetPlanetsUseCaseTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var planetsRepository: PlanetsRepository

    private lateinit var planetsUseCase: GetPlanetsUseCase

    @Before
    fun setUp() {
        planetsUseCase = GetPlanetsUseCase(planetsRepository)
    }

    @Test
    fun `get planets should return success with empty list`() = runTest {
        whenever(planetsRepository.getAllPlanets()).thenReturn(
            flow {
                emit(Result.Success(emptyList()))
            }
        )

        planetsUseCase().test {
            val result = awaitItem()
            assertEquals(true, result.data.isNullOrEmpty())
            awaitComplete()
        }
    }


    @Test
    fun `get Planets should return success with default non empty list`() =
        runTest {
            whenever(planetsRepository.getAllPlanets()).thenReturn(
                flow {
                    emit(Result.Success(TestConstant.planets))
                }
            )

            planetsUseCase().test {
                val result = awaitItem()
                assertEquals(true, result.data?.isNotEmpty())
                awaitComplete()
            }
        }

    @Test
    fun `get planets should return success with non empty list`() = runTest {
        whenever(planetsRepository.getAllPlanets()).thenReturn(
            flow {
                emit(Result.Success(TestConstant.planets))
            }
        )

        planetsUseCase().test {
            val result = awaitItem()
            assertEquals(true, result.data?.isNotEmpty())
            assertEquals(TestConstant.resultDto.uid, result.data?.get(0)?.uid)
            awaitComplete()
        }
    }

    @Test
    fun `get planets from offline data when network call fails return Failure`() = runTest {
        whenever(planetsRepository.getAllPlanets()).thenReturn(
            flow {
                emit(Result.Failure("error"))
                emit(Result.Success(TestConstant.planets))
            }
        )

        planetsUseCase().test {
            awaitItem()
            val result = awaitItem()
            assertEquals(true, result.data?.isNotEmpty())
            assertEquals(TestConstant.resultDto.uid, result.data?.get(0)?.uid)
            awaitComplete()
        }
    }

    @Test
    fun `get planets from offline data return Loading data`() = runTest {
        whenever(planetsRepository.getAllPlanets()).thenReturn(
            flow {
                emit(Result.Loading())
                emit(Result.Success(TestConstant.planets))
            }
        )

        planetsUseCase().test {
            awaitItem()
            val result = awaitItem()
            assertEquals(true, result.data?.isNotEmpty())
            assertEquals(TestConstant.resultDto.uid, result.data?.get(0)?.uid)
            awaitComplete()
        }
    }


    @After
    fun tearDown() {

    }
}