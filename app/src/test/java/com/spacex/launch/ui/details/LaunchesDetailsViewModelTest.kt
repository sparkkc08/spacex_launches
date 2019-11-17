package com.spacex.launch.ui.details

import android.view.View
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.spacex.launch.data.model.SpaceXLaunch
import com.spacex.launch.utils.API_DATE_FORMAT
import org.junit.Test
import java.io.FileReader
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue


class LaunchesDetailsViewModelTest {

    private val launches: List<SpaceXLaunch>
    private val viewModel: LaunchesDetailsViewModel

    init {
        val gson = GsonBuilder()
            .setDateFormat(API_DATE_FORMAT)
            .setLenient()
            .create()

        val reader = FileReader("src/test/java/resources/launches_test_data.json")
        val listType = object : TypeToken<List<SpaceXLaunch>>() {}.type

        launches = gson.fromJson<List<SpaceXLaunch>>(
            reader,
            listType
        )

        viewModel = LaunchesDetailsViewModel()
    }

    @Test
    fun `test past launch with all data`() {
        val launch = launches.first()
        viewModel.setLaunch(launch)

        assertEquals(launch.missionName, viewModel.mission.get())
        assertEquals("January 6, 2014 8:06 PM", viewModel.launchDate.get())
        assertEquals(launch.launchSite.siteName, viewModel.launchSiteName.get())
        assertEquals(launch.rocket.rocketName, viewModel.rocketName.get())
        assertEquals(launch.rocket.rocketType, viewModel.rocketType.get())
        assertEquals(true.toString(), viewModel.launchStatus.get())
        assertEquals(launch.details, viewModel.launchDetails.get())

        assertEquals("Thaicom", viewModel.customer.get())

        assertEquals(View.VISIBLE, viewModel.galleryVisibility.get())

        assertEquals(launch.links.article_link, viewModel.articleLink.get())
        assertEquals(launch.links.wikipedia, viewModel.wikipediaLink.get())
        assertEquals(launch.links.video_link, viewModel.videoLink.get())

        assertEquals(View.VISIBLE, viewModel.detailsVisibility.get())
    }

    @Test
    fun `test upcoming launch with lack of some data`() {
        val launch = launches.last()
        viewModel.setLaunch(launch)

        assertEquals(launch.missionName, viewModel.mission.get())
        assertEquals("October 15, 2019 3:00 AM", viewModel.launchDate.get())
        assertEquals(launch.launchSite.siteName, viewModel.launchSiteName.get())
        assertEquals(launch.rocket.rocketName, viewModel.rocketName.get())
        assertEquals(launch.rocket.rocketType, viewModel.rocketType.get())
        assertNull(viewModel.launchStatus.get())
        assertEquals(launch.details, viewModel.launchDetails.get())

        assertEquals("NASA (CRS)", viewModel.customer.get())

        assertEquals(View.GONE, viewModel.galleryVisibility.get())

        assertNull(viewModel.articleLink.get())
        assertNull(viewModel.wikipediaLink.get())
        assertNull(viewModel.videoLink.get())

        assertEquals(View.GONE, viewModel.detailsVisibility.get())
    }

    @Test
    fun isAllEmptyTest() {
        assertTrue(viewModel.isAllEmpty())
        assertTrue(viewModel.isAllEmpty("", null, "", null))
        assertFalse(viewModel.isAllEmpty("", null, "", null, "test"))
    }
}