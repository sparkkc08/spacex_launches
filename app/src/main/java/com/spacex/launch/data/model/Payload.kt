package com.spacex.launch.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Payload(
    val customers: List<String>,
    val manufacturer: String,
    val nationality: String,
    val norad_id: List<Int>,
    val orbit: String,
    val orbit_params: OrbitParams,
    val payload_id: String,
    val payload_mass_kg: Float?,
    val payload_mass_lbs: Float?,
    val payload_type: String,
    val reused: Boolean
) : Parcelable