package com.spacex.launch.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Fairings(
    val recovered: Boolean,
    val recovery_attempt: @RawValue Any,
    val reused: Boolean,
    val ship: @RawValue Any
) : Parcelable