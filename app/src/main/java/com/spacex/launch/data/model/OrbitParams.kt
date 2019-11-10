package com.spacex.launch.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class OrbitParams(
    val apoapsis_km: @RawValue Any,
    val arg_of_pericenter: @RawValue Any,
    val eccentricity: @RawValue Any,
    val epoch: @RawValue Any,
    val inclination_deg: @RawValue Any,
    val lifespan_years: @RawValue Any,
    val longitude: @RawValue Any,
    val mean_anomaly: @RawValue Any,
    val mean_motion: @RawValue Any,
    val periapsis_km: @RawValue Any,
    val period_min: @RawValue Any,
    val raan: @RawValue Any,
    val reference_system: String,
    val regime: String,
    val semi_major_axis_km: @RawValue Any
) : Parcelable