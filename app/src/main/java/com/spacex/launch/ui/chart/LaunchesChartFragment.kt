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
import javax.inject.Inject


/**
 * A placeholder fragment containing a simple view.
 */
class LaunchesChartFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var chartViewModel: ChartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        chartViewModel = injectViewModel<ChartViewModel>(viewModelFactory).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }

        val binding = DataBindingUtil.inflate<FragmentLaunchesChartBinding>(
            inflater, R.layout.fragment_launches_chart, container, false
        )

        binding.executePendingBindings()

        return binding.root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): LaunchesChartFragment {
            return LaunchesChartFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}