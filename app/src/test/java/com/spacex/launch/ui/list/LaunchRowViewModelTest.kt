package com.spacex.launch.ui.list

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.spacex.launch.data.model.SpaceXLaunch
import com.spacex.launch.utils.API_DATE_FORMAT
import com.spacex.launch.utils.SingleLiveEvent
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import io.mockk.verify
import org.junit.Test
import java.io.FileReader
import java.text.DateFormat
import java.text.SimpleDateFormat
import kotlin.test.assertEquals


class LaunchRowViewModelTest {
    private val launchDateFormat: DateFormat = SimpleDateFormat.getDateInstance()
    @MockK(relaxed = true)
    private lateinit var itemClicked: SingleLiveEvent<SpaceXLaunch>

    private val launch: SpaceXLaunch
    private val viewModel: LaunchRowViewModel

    init {
        MockKAnnotations.init(this)
        val gson = GsonBuilder()
            .setDateFormat(API_DATE_FORMAT)
            .setLenient()
            .create()

        val reader = FileReader("src/test/java/resources/launches_test_data.json")
        val listType = object : TypeToken<List<SpaceXLaunch>>() {}.type

        launch = spyk(
            gson.fromJson<List<SpaceXLaunch>>(
                reader,
                listType
            ).first()
        )

        viewModel = LaunchRowViewModel(launch, launchDateFormat, itemClicked)
    }

    @Test
    fun `test launch with all data`() {
        assertEquals(launch.links.missionPatchSmall, viewModel.getMissionLogo())
        assertEquals(launch.missionName, viewModel.getMissionName())
        assertEquals(launch.rocket.rocketName, viewModel.getRocketName())
        assertEquals(launch.launchDateLocal, viewModel.getMissionDate())
        assertEquals(launchDateFormat, viewModel.dateFormat)

        every { launch.upcoming } returns false
        every { launch.launchSuccess } returns true
        assertEquals(android.R.color.holo_green_light, viewModel.getMissionStatusColor())
        every { launch.upcoming } returns true
        assertEquals(android.R.color.transparent, viewModel.getMissionStatusColor())
        every { launch.launchSuccess } returns false
        every { launch.upcoming } returns false
        assertEquals(android.R.color.holo_orange_light, viewModel.getMissionStatusColor())

        viewModel.getClickListener().onClick(null)
        verify(exactly = 1) { itemClicked.value = launch }
    }
}