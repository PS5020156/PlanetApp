package com.jpmc.planetapp.di

import android.content.Context
import androidx.room.Room
import com.jpmc.planetapp.core.utils.Constant
import com.jpmc.planetapp.di.qualifier.IODispatcher
import com.jpmc.planetapp.planets_feature.data.api.PlanetsApi
import com.jpmc.planetapp.planets_feature.data.local.PlanetsDatabase
import com.jpmc.planetapp.planets_feature.data.local.dao.PlanetsDao
import com.jpmc.planetapp.planets_feature.data.repository.PlanetsRepositoryImpl
import com.jpmc.planetapp.planets_feature.domain.repository.PlanetsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PlanetsModule {
    @Provides
    @Singleton
    fun providePlanetsApi(retrofit: Retrofit): PlanetsApi = retrofit
        .create(PlanetsApi::class.java)

    @Provides
    @Singleton
    fun providePlanetsRepository(
        planetsApi: PlanetsApi,
        planetsDao: PlanetsDao,
        @IODispatcher dispatcher: CoroutineDispatcher
    ): PlanetsRepository =
        PlanetsRepositoryImpl(planetsApi, planetsDao, dispatcher)

    @Provides
    @Singleton
    fun providePlanetsDatabase(@ApplicationContext context: Context): PlanetsDatabase =
        Room.databaseBuilder(context, PlanetsDatabase::class.java, Constant.PLANETS_DB).build()

    @Provides
    @Singleton
    fun providePlanetsDao(planetsDatabase: PlanetsDatabase) =
        planetsDatabase.getPlanetsDao()
}