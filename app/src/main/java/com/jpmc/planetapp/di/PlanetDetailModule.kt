package com.jpmc.planetapp.di

import android.content.Context
import androidx.room.Room
import com.jpmc.planetapp.core.utils.Constant
import com.jpmc.planetapp.di.qualifier.IODispatcher
import com.jpmc.planetapp.planet_feature_detail.data.api.PlanetDetailApi
import com.jpmc.planetapp.planet_feature_detail.data.local.PlanetDetailDatabase
import com.jpmc.planetapp.planet_feature_detail.data.local.dao.PlanetDetailDao
import com.jpmc.planetapp.planet_feature_detail.data.repository.PlanetDetailRepositoryImpl
import com.jpmc.planetapp.planet_feature_detail.domain.repository.PlanetDetailRepository
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
object PlanetDetailModule {

    @Provides
    @Singleton
    fun providePlanetDetailApi(retrofit: Retrofit): PlanetDetailApi = retrofit
        .create(PlanetDetailApi::class.java)

    @Provides
    @Singleton
    fun providePlanetDetailRepository(
        planetDetailApi: PlanetDetailApi,
        planetDetailDao: PlanetDetailDao,
        @IODispatcher dispatcher: CoroutineDispatcher
    ): PlanetDetailRepository =
        PlanetDetailRepositoryImpl(planetDetailApi, planetDetailDao, dispatcher)

    @Provides
    @Singleton
    fun providePlanetDetailDatabase(@ApplicationContext context: Context): PlanetDetailDatabase =
        Room.databaseBuilder(context, PlanetDetailDatabase::class.java, Constant.PLANET_DETAIL_DB)
            .build()

    @Provides
    @Singleton
    fun providePlanetDetailDao(planetDetailDatabase: PlanetDetailDatabase) =
        planetDetailDatabase.getPlanetDetailDao()

}