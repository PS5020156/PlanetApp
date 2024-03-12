package com.jpmc.planetapp.planet_feature_detail.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jpmc.planetapp.planet_feature_detail.data.api.PlanetDetailApi
import com.jpmc.planetapp.planet_feature_detail.data.local.dao.PlanetDetailDao
import com.jpmc.planetapp.planet_feature_detail.domain.model.PlanetDetail
import com.jpmc.planetapp.planet_feature_detail.domain.repository.PlanetDetailRepository
import com.jpmc.planetapp.utils.MainCoroutineRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PlanetDetailRepositoryImplTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var planetDetailApi: PlanetDetailApi

    @Mock
    private lateinit var planetDetailDao: PlanetDetailDao

    @Mock
    private lateinit var planetDetailRepository: PlanetDetailRepository

    private lateinit var planetDetail: PlanetDetail

    @Test
    fun setUp() {
        planetDetailRepository = PlanetDetailRepositoryImpl(
            planetDetailApi,
            planetDetailDao,
            mainCoroutineRule.testDispatcher
        )
        planetDetail = PlanetDetail()
    }
}