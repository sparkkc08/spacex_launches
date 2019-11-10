package com.spacex.launch.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.spacex.launch.data.model.SpaceXLaunch
import com.spacex.launch.databinding.ListItemLaunchBinding
import java.text.DateFormat
import java.text.SimpleDateFormat

class LaunchesAdapter(private val itemClicked: MutableLiveData<SpaceXLaunch>) :
    PagedListAdapter<SpaceXLaunch, LaunchesAdapter.ViewHolder>(LaunchesDiffCallback()) {

    private val launchDateFormat: DateFormat = SimpleDateFormat.getDateInstance()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val launch = getItem(position)
        launch?.let {
            holder.apply {
                bind(launch, launchDateFormat, itemClicked)
                itemView.tag = launch.mission_id
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemLaunchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class ViewHolder(private val binding: ListItemLaunchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            launch: SpaceXLaunch,
            format: DateFormat,
            itemClicked: MutableLiveData<SpaceXLaunch>
        ) {
            binding.apply {
                viewModel = LaunchRowViewModel(launch, format, itemClicked)
                executePendingBindings()
            }
        }
    }
}

private class LaunchesDiffCallback : DiffUtil.ItemCallback<SpaceXLaunch>() {

    override fun areItemsTheSame(oldItem: SpaceXLaunch, newItem: SpaceXLaunch): Boolean {
        return oldItem.mission_id == newItem.mission_id
    }

    override fun areContentsTheSame(oldItem: SpaceXLaunch, newItem: SpaceXLaunch): Boolean {
        return oldItem == newItem
    }
}