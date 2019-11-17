package com.spacex.launch.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrbitParams(
    val apoapsis_km: Float?,
    val arg_of_pericenter: Float?,
    val eccentricity: Float?,
    val epoch: String?,
    val inclination_deg: Float?,
    val lifespan_years: Float?,
    val longitude: Float?,
    val mean_anomaly: Float?,
    val mean_motion: Float?,
    val periapsis_km: Float?,
    val period_min: Float?,
    val raan: Float?,
    val reference_system: String,
    val regime: String,
    val semi_major_axis_km: Float?
) : Parcelable