package com.spacex.launch.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class SecondStage(
    val block: @RawValue Any,
    val payloads: List<Payload>
) : Parcelable