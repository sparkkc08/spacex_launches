package com.spacex.launch.di


import com.spacex.launch.api.SpaceXApi
import com.spacex.launch.data.launches.LaunchesRepository
import com.spacex.launch.data.launches.LaunchesRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@Suppress("unused")
object RepositoryModule {

    @Provides
    @Singleton
    @JvmStatic
    internal fun provideLaunchesRepo(spaceXApi: SpaceXApi): LaunchesRepository {
        return LaunchesRepositoryImpl(spaceXApi)
    }
}
