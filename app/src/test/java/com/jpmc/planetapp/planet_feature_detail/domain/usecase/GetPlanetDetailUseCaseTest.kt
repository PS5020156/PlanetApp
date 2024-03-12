package com.jpmc.planetapp.planet_feature_detail.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.jpmc.planetapp.core.common.Result
import com.jpmc.planetapp.planet_feature_detail.domain.model.PlanetDetail
import com.jpmc.planetapp.planet_feature_detail.domain.repository.PlanetDetailRepository
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
class GetPlanetDetailUseCaseTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var planetDetailRepository: PlanetDetailRepository

    private lateinit var planetDetailUseCase: GetPlanetDetailUseCase

    @Before
    fun setUp() {
        planetDetailUseCase = GetPlanetDetailUseCase(planetDetailRepository)
    }

    @Test
    fun `get planet detail should return success with one item`() = runTest {
        whenever(planetDetailRepository.getPlanetDetail(TestConstant.ID)).thenReturn(
            flow {
                emit(Result.Success(TestConstant.planetDetail))
            }
        )

        planetDetailUseCase(TestConstant.ID).test {
            val result = awaitItem()
            assertEquals(TestConstant.ID, result.data?.id)
            awaitComplete()
        }
    }


    @Test
    fun `get Planets should return success with default value`() =
        runTest {
            whenever(planetDetailRepository.getPlanetDetail(TestConstant.ID)).thenReturn(
                flow {
                    emit(Result.Success(PlanetDetail()))
                }
            )

            planetDetailUseCase(TestConstant.ID).test {
                val result = awaitItem()
                assertEquals("", result.data?.id)
                awaitComplete()
            }
        }

    @Test
    fun `get planets from offline data when network call fails return Failure`() = runTest {
        whenever(planetDetailRepository.getPlanetDetail(TestConstant.ID)).thenReturn(
            flow {
                emit(Result.Failure("error"))
                emit(Result.Success(TestConstant.planetDetail))
            }
        )

        planetDetailUseCase(TestConstant.ID).test {
            awaitItem()
            val result = awaitItem()
            assertEquals(TestConstant.ID, result.data?.id)
            assertEquals(TestConstant.planetDetail.name, result.data?.name)
            awaitComplete()
        }
    }

    @Test
    fun `get planets from offline data return Loading data`() = runTest {
        whenever(planetDetailRepository.getPlanetDetail(TestConstant.ID)).thenReturn(
            flow {
                emit(Result.Loading())
                emit(Result.Success(TestConstant.planetDetail))
            }
        )

        planetDetailUseCase(TestConstant.ID).test {
            awaitItem()
            val result = awaitItem()
            assertEquals(TestConstant.planetDetail.id, result.data?.id)
            awaitComplete()
        }
    }


    @After
    fun tearDown() {

    }
}