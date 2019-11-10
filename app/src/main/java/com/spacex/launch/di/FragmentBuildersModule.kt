package com.spacex.launch.di

import com.spacex.launch.ui.chart.LaunchesChartFragment
import com.spacex.launch.ui.details.LaunchDetailsFragment
import com.spacex.launch.ui.list.LaunchesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeListFragment(): LaunchesListFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailsFragment(): LaunchDetailsFragment

    @ContributesAndroidInjector
    abstract fun contributeChartFragment(): LaunchesChartFragment
}