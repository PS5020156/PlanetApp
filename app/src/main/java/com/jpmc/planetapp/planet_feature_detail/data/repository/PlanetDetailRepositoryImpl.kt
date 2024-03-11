package com.jpmc.planetapp.planet_feature_detail.data.repository

import com.jpmc.planetapp.core.common.Result
import com.jpmc.planetapp.planet_feature_detail.data.api.PlanetDetailApi
import com.jpmc.planetapp.planet_feature_detail.data.dto.PlanetDetailDto
import com.jpmc.planetapp.planet_feature_detail.data.local.dao.PlanetDetailDao
import com.jpmc.planetapp.planet_feature_detail.data.mapper.toDomainPlanetDetail
import com.jpmc.planetapp.planet_feature_detail.data.mapper.toPlanetDetailEntity
import com.jpmc.planetapp.planet_feature_detail.domain.model.PlanetDetail
import com.jpmc.planetapp.planet_feature_detail.domain.repository.PlanetDetailRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.UnknownHostException
import javax.inject.Inject

@ViewModelScoped
class PlanetDetailRepositoryImpl @Inject constructor(
    private val planetDetailApi: PlanetDetailApi,
    private val planetDetailDao: PlanetDetailDao,
    private val ioDispatcher: CoroutineDispatcher
) : PlanetDetailRepository {
    override fun getPlanetDetail(id: String): Flow<Result<PlanetDetail>> = flow {
        emit(Result.Loading())

        val planetDetail = planetDetailDao.getPlanetDetail(id)

        if (planetDetail != null) {
            emit(Result.Success(planetDetail.toDomainPlanetDetail()))
        }

        try {
            val planetDetailApi: PlanetDetailDto = planetDetailApi.getPlanetDetails(id)
            planetDetailDao.insertPlanetDetail(planetDetailApi.result.toPlanetDetailEntity())
            emit(Result.Success(planetDetailDao.getPlanetDetail(id).toDomainPlanetDetail()))
        } catch (e: UnknownHostException) {
            if (planetDetail != null)
                emit(Result.Success(planetDetail.toDomainPlanetDetail()))
        }

    }.flowOn(ioDispatcher)
        .catch {
            emit(Result.Failure(it.message))
        }
}