package com.spacex.launch.ui.chart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.spacex.launch.R
import com.spacex.launch.databinding.FragmentLaunchesChartBinding
import com.spacex.launch.di.Injectable
import com.spacex.launch.di.injectViewModel
import kotlinx.android.synthetic.main.fragment_launches_chart.*
import javax.inject.Inject


private const val SELECTED_INDEX = "selected_index"

class LaunchesChartFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var chartViewModel: ChartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        chartViewModel = injectViewModel(viewModelFactory)

        val binding = DataBindingUtil.inflate<FragmentLaunchesChartBinding>(
            inflater, R.layout.fragment_launches_chart, container, false
        )

        binding.viewModel = chartViewModel
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(SELECTED_INDEX, chartSuccess.getHighlightedIndex() ?: -1)
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.getInt(SELECTED_INDEX, -1)?.takeIf { it >= 0 }?.let {
            chartSuccess.setHighlightedIndex(it)
        }
    }
}