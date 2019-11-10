package com.spacex.launch.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Payload(
    val customers: List<String>,
    val manufacturer: String,
    val nationality: String,
    val norad_id: @RawValue List<Any>,
    val orbit: String,
    val orbit_params: OrbitParams,
    val payload_id: String,
    val payload_mass_kg: @RawValue Any,
    val payload_mass_lbs: @RawValue Any,
    val payload_type: String,
    val reused: Boolean
) : Parcelable