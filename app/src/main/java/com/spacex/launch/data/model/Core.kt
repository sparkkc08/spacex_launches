package com.spacex.launch.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Core(
    val block: Float?,
    val core_serial: String?,
    val flight: Int?,
    val gridfins: Boolean?,
    val land_success: Boolean?,
    val landing_intent: Boolean?,
    val landing_type: String?,
    val landing_vehicle: String?,
    val legs: Boolean?,
    val reused: Boolean?
) : Parcelable