package com.spacex.launch.data.launches

import com.spacex.launch.api.SpaceXApi
import com.spacex.launch.data.model.SpaceXLaunch
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LaunchesRepositoryImpl @Inject constructor(private val spaceApi: SpaceXApi) :
    LaunchesRepository {

    override fun getPastLaunches(): Single<List<SpaceXLaunch>> {
        return spaceApi.getPastLaunches()
    }

    override fun getAllLaunchesPage(offset: Int, limit: Int): Single<List<SpaceXLaunch>> {
        return spaceApi.getAllLaunchesPage(offset, limit)
    }
}