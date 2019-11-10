package com.spacex.launch.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Telemetry(
    val flight_club: @RawValue Any
) : Parcelable