package com.spacex.launch.ui.details

import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.spacex.launch.data.model.SpaceXLaunch
import com.spacex.launch.utils.BaseViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import javax.inject.Inject

class LaunchesDetailsViewModel @Inject constructor() :
    BaseViewModel() {
    private val launchDateFormat: DateFormat =
        SimpleDateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT)

    val mission = ObservableField<String>()
    val launchDate = ObservableField<String>()
    val launchSiteName = ObservableField<String>()
    val launchStatus = ObservableField<String>()
    val rocketName = ObservableField<String>()
    val rocketType = ObservableField<String>()
    val customer = ObservableField<String>()
    val launchDetails = ObservableField<String>()
    val articleLink = ObservableField<String>()
    val wikipediaLink = ObservableField<String>()
    val videoLink = ObservableField<String>()
    val detailsVisibility = ObservableInt(View.GONE)
    val galleryAdapter = ObservableField<GalleryViewPagerAdapter>()
    val galleryVisibility = ObservableInt(View.GONE)

    fun setLaunch(launch: SpaceXLaunch) {
        with(launch) {
            mission.set(missionName)
            launchDate.set(launchDateFormat.format(launchDateLocal))
            launchSiteName.set(launchSite.siteName)
            rocketName.set(rocket.rocketName)
            rocketType.set(rocket.rocketType)
            launchDetails.set(details)

            if (!upcoming) {
                launchStatus.set(launchSuccess.toString())
            }

            with(rocket.secondStage.payloads) {
                val customers = mutableListOf<String>()
                forEach { customers.addAll(it.customers) }
                customer.set(customers.joinToString())
            }

            with(links) {
                if (flickrImages.isNotEmpty()) {
                    galleryAdapter.set(GalleryViewPagerAdapter(links.flickrImages))
                    galleryVisibility.set(View.VISIBLE)
                }

                articleLink.set(article_link)
                wikipediaLink.set(wikipedia)
                videoLink.set(video_link)

                detailsVisibility.set(
                    if (isAllEmpty(details, article_link, wikipedia, video_link)) {
                        View.GONE
                    } else {
                        View.VISIBLE
                    }
                )

            }
        }
    }

    @VisibleForTesting
    fun isAllEmpty(vararg strings: String?): Boolean {
        strings.forEach {
            if (!it.isNullOrEmpty()) {
                return false
            }
        }

        return true
    }
}