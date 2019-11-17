package com.spacex.launch.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SecondStage(
    val block: Float?,
    val payloads: List<Payload>
) : Parcelable