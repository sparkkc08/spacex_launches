package com.spacex.launch.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Core(
    val block: @RawValue Any,
    val core_serial: @RawValue Any,
    val flight: @RawValue Any,
    val gridfins: @RawValue Any,
    val land_success: @RawValue Any,
    val landing_intent: @RawValue Any,
    val landing_type: @RawValue Any,
    val landing_vehicle: @RawValue Any,
    val legs: @RawValue Any,
    val reused: @RawValue Any
) : Parcelable