package com.spacex.launch.ui.chart

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.spacex.launch.RxImmediateSchedulerRule
import com.spacex.launch.data.launches.LaunchesRepository
import com.spacex.launch.data.model.SpaceXLaunch
import com.spacex.launch.utils.API_DATE_FORMAT
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.ClassRule
import org.junit.Test
import java.io.FileReader


class ChartViewModelTest {
    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    private val launches: List<SpaceXLaunch>
    private lateinit var viewModel: ChartViewModel
    @MockK(relaxed = true)
    private lateinit var launchesRepository: LaunchesRepository

    init {
        MockKAnnotations.init(this)
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
    }

    @Test
    fun getChartData() {
        every { launchesRepository.getPastLaunches() } returns Single.just(launches)

        viewModel = ChartViewModel(launchesRepository)

        assertEquals(listOf(Pair("01/14", 1), Pair("01/16", 1)), viewModel.chartData.get())
    }
}