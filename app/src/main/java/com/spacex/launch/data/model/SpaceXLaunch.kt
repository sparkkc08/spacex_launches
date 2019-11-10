package com.spacex.launch.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class SpaceXLaunch(
    val details: String,
    val flight_number: Int,
    val is_tentative: Boolean,
    @SerializedName("launch_date_local")
    val launchDateLocal: Date,
    val launch_date_unix: Int,
    val launch_date_utc: Date,
    val launch_failure_details: LaunchFailureDetails,
    @SerializedName("launch_site")
    val launchSite: LaunchSite,
    @SerializedName("launch_success")
    val launchSuccess: Boolean,
    val launch_window: Int,
    val launch_year: String,
    val links: Links,
    val mission_id: List<String>,
    @SerializedName("mission_name")
    val missionName: String,
    val rocket: Rocket,
    val ships: List<String>,
    val static_fire_date_unix: Int,
    val static_fire_date_utc: String,
    val tbd: Boolean,
    val telemetry: Telemetry,
    val tentative_max_precision: String,
    val timeline: Timeline,
    val upcoming: Boolean
) : Parcelable