package com.spacex.launch.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Timeline(
    val engine_chill: Int,
    val fairing_deploy: Int,
    val first_stage_boostback_burn: Int,
    val first_stage_entry_burn: Int,
    val first_stage_landing: Int,
    val go_for_launch: Int,
    val go_for_prop_loading: Int,
    val ignition: Int,
    val liftoff: Int,
    val maxq: Int,
    val meco: Int,
    val payload_deploy: Int,
    val prelaunch_checks: Int,
    val propellant_pressurization: Int,
    val rp1_loading: Int,
    val `seco-1`: Int,
    val `seco-2`: Int,
    val second_stage_ignition: Int,
    val second_stage_restart: Int,
    val stage1_lox_loading: Int,
    val stage2_lox_loading: Int,
    val stage_sep: Int,
    val webcast_liftoff: Int
) : Parcelable