package com.spacex.launch.api

import com.spacex.launch.data.model.SpaceXLaunch
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SpaceXApi {
    @GET("/v3/launches/past")
    fun getPastLaunches(): Single<List<SpaceXLaunch>>

    @GET("/v3/launches")
    fun getAllLaunchesPage(@Query("offset") offset: Int, @Query("limit") limit: Int): Single<List<SpaceXLaunch>>
}