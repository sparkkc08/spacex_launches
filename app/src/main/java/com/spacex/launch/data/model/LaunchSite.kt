package com.spacex.launch.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LaunchSite(
    val site_id: String,
    @SerializedName("site_name")
    val siteName: String,
    val site_name_long: String
) : Parcelable