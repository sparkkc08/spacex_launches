package com.spacex.launch.ui.list

import androidx.paging.PagedList
import com.spacex.launch.data.launches.LaunchesRepository
import com.spacex.launch.data.model.SpaceXLaunch
import com.spacex.launch.utils.BaseViewModel
import com.spacex.launch.utils.MainThreadExecutor
import com.spacex.launch.utils.SingleLiveEvent
import java.util.concurrent.Executors
import javax.inject.Inject

private const val PAGE_SIZE = 10

class LaunchesListViewModel @Inject constructor(launchesRepository: LaunchesRepository) :
    BaseViewModel() {

    val itemClicked = SingleLiveEvent<SpaceXLaunch>()
    val adapter: LaunchesAdapter by lazy { LaunchesAdapter(itemClicked) }

    init {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE)
            .build()

        val dataSource = LaunchesDataSource(launchesRepository, compositeDisposable)

        val pagedList = PagedList.Builder(dataSource, config)
            .setNotifyExecutor(MainThreadExecutor())
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .build()

        adapter.submitList(pagedList)
    }
}