package com.spacex.launch.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.spacex.launch.R
import com.spacex.launch.databinding.FragmentLaunchesListBinding
import com.spacex.launch.di.Injectable
import com.spacex.launch.di.injectViewModel
import com.spacex.launch.ui.details.LaunchDetailsFragment
import javax.inject.Inject

class LaunchesListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var listViewModel: LaunchesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listViewModel = injectViewModel(viewModelFactory)

        val binding = DataBindingUtil.inflate<FragmentLaunchesListBinding>(
            inflater, R.layout.fragment_launches_list, container, false
        )

        listViewModel.itemClicked.observe(
            viewLifecycleOwner,
            Observer {
                activity?.run {
                    val tag = LaunchDetailsFragment::class.java.simpleName
                    supportFragmentManager.beginTransaction()
                        .add(R.id.container, LaunchDetailsFragment.newInstance(it), tag)
                        .addToBackStack(tag)
                        .commit()
                }
            })

        binding.viewModel = listViewModel

        return binding.root
    }
}