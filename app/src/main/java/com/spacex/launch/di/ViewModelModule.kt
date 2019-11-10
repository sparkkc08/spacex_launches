package com.spacex.launch.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spacex.launch.ui.chart.ChartViewModel
import com.spacex.launch.ui.details.LaunchesDetailsViewModel
import com.spacex.launch.ui.list.LaunchesListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ChartViewModel::class)
    abstract fun bindThemeViewModel(viewModel: ChartViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LaunchesListViewModel::class)
    abstract fun bindListViewModel(viewModel: LaunchesListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LaunchesDetailsViewModel::class)
    abstract fun bindDetailsViewModel(viewModel: LaunchesDetailsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}