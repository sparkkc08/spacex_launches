package com.spacex.launch.ui.list

import androidx.paging.PositionalDataSource
import com.spacex.launch.data.launches.LaunchesRepository
import com.spacex.launch.data.model.SpaceXLaunch
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LaunchesDataSource @Inject constructor(
    private val repository: LaunchesRepository,
    private val compositeDisposable: CompositeDisposable
) : PositionalDataSource<SpaceXLaunch>() {


    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<SpaceXLaunch>) {
        compositeDisposable.add(
            repository.getAllLaunchesPage(params.startPosition, params.loadSize)
                .subscribe({ users ->
                    callback.onResult(users)
                }, { throwable -> throwable.printStackTrace() })
        )
    }

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<SpaceXLaunch>
    ) {
        compositeDisposable.add(
            repository.getAllLaunchesPage(params.requestedStartPosition, params.requestedLoadSize)
                .subscribe({ users ->
                    callback.onResult(users, 0)
                }, { throwable -> throwable.printStackTrace() })
        )
    }
}