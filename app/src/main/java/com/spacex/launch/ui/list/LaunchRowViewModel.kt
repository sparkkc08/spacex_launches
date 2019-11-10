package com.spacex.launch.ui.list

import android.view.View
import androidx.annotation.ColorRes
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.spacex.launch.data.model.SpaceXLaunch
import java.text.DateFormat
import java.util.*

class LaunchRowViewModel(
    private val launch: SpaceXLaunch,
    val dateFormat: DateFormat,
    private val itemClicked: MutableLiveData<SpaceXLaunch>
) : BaseObservable() {

    @Bindable
    fun getMissionLogo(): String? {
        return launch.links.missionPatchSmall
    }

    @Bindable
    fun getMissionName(): String {
        return launch.missionName
    }

    @Bindable
    fun getRocketName(): String {
        return launch.rocket.rocketName
    }

    @Bindable
    fun getMissionDate(): Date {
        return launch.launchDateLocal
    }

    @ColorRes
    @Bindable
    fun getMissionStatusColor(): Int {
        return when {
            launch.upcoming -> android.R.color.transparent
            launch.launchSuccess -> android.R.color.holo_green_light
            else -> android.R.color.holo_orange_light
        }
    }

    @Bindable
    fun getClickListener(): View.OnClickListener {
        return View.OnClickListener {
            itemClicked.value = launch
        }
    }
}