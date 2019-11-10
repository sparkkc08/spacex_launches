package com.spacex.launch.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.spacex.launch.R
import com.spacex.launch.data.model.SpaceXLaunch
import com.spacex.launch.databinding.FragmentLaunchDetailsBinding
import com.spacex.launch.di.Injectable
import com.spacex.launch.di.injectViewModel
import javax.inject.Inject

class LaunchDetailsFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var detailsViewModel: LaunchesDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailsViewModel = injectViewModel(viewModelFactory)

        val binding = DataBindingUtil.inflate<FragmentLaunchDetailsBinding>(
            inflater, R.layout.fragment_launch_details, container, false
        )

        arguments?.run {
            getParcelable<SpaceXLaunch>(LAUNCH_DATA)?.also {
                detailsViewModel.setLaunch(it)
            }
        }
        2
        with(binding) {
            viewpager.offscreenPageLimit = 3
            viewModel = detailsViewModel
            return root
        }
    }

    companion object {
        private const val LAUNCH_DATA = "launch_data"

        fun newInstance(launch: SpaceXLaunch): LaunchDetailsFragment {
            return LaunchDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(LAUNCH_DATA, launch)
                }
            }
        }
    }
}