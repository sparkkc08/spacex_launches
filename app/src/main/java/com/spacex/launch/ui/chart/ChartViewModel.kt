package com.spacex.launch.ui.chart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.spacex.launch.data.launches.LaunchesRepository
import com.spacex.launch.utils.BaseViewModel
import javax.inject.Inject

class ChartViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var launchesRepository: LaunchesRepository

    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it"
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}