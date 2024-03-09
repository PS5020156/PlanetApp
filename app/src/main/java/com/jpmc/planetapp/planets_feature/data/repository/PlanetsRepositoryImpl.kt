package com.jpmc.planetapp.planets_feature.data.repository

import com.jpmc.planetapp.core.common.Result
import com.jpmc.planetapp.planets_feature.data.api.PlanetsApi
import com.jpmc.planetapp.planets_feature.data.local.dao.PlanetsDao
import com.jpmc.planetapp.planets_feature.data.mapper.toDomainPlanets
import com.jpmc.planetapp.planets_feature.data.mapper.toPlanetsEntity
import com.jpmc.planetapp.planets_feature.domain.model.Planets
import com.jpmc.planetapp.planets_feature.domain.repository.PlanetsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.UnknownHostException
import javax.inject.Inject

@ViewModelScoped
class PlanetsRepositoryImpl @Inject constructor(
    private val planetsApi: PlanetsApi,
    private val planetsDao: PlanetsDao,
    private val ioDispatcher: CoroutineDispatcher
) : PlanetsRepository {
    override fun getAllPlanets(): Flow<Result<List<Planets>>> = flow {
        emit(Result.Loading())

        val planetsCache = planetsDao.getPlanets().map {
            it.toDomainPlanets()
        }
        emit(Result.Success(planetsCache))

        try {
            val planets = planetsApi.getAllPlanets().results.map {
                it.toPlanetsEntity()
            }
            planetsDao.deletePlanets()
            planetsDao.insertPlanets(planets)
            emit(Result.Success(planets.map {
                it.toDomainPlanets()
            }))
        } catch (e: UnknownHostException) {
            if (planetsCache.isEmpty())
                emit(Result.Failure(e.message)) else
                emit(Result.Success(planetsCache))
        }
    }.flowOn(ioDispatcher)
        .catch {
            emit(Result.Failure(it.message))
        }
}