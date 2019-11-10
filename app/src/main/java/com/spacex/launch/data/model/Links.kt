package com.spacex.launch.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Links(
    val article_link: String?,
    @SerializedName("flickr_images")
    val flickrImages: List<String>,
    @SerializedName("mission_patch")
    val missionPatch: String,
    @SerializedName("mission_patch_small")
    val missionPatchSmall: String?,
    val presskit: String?,
    val reddit_campaign: String?,
    val reddit_launch: String?,
    val reddit_media: String?,
    val reddit_recovery: String?,
    val video_link: String?,
    val wikipedia: String?,
    val youtube_id: String?
) : Parcelable