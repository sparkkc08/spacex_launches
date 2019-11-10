package com.spacex.launch.data.launches

import com.spacex.launch.data.model.SpaceXLaunch
import io.reactivex.Single

interface LaunchesRepository {
    fun getAllLaunches(): Single<List<SpaceXLaunch>>
    fun getAllLaunchesPage(offset: Int, limit: Int): Single<List<SpaceXLaunch>>
}