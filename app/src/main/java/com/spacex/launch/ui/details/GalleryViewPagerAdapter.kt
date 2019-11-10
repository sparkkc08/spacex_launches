package com.spacex.launch.ui.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.viewpager.widget.PagerAdapter
import com.spacex.launch.databinding.GalleryItemBinding


class GalleryViewPagerAdapter(
    private val links: List<String>
) : PagerAdapter() {

    override fun getCount(): Int {
        return links.size
    }

    override fun isViewFromObject(@NonNull view: View, @NonNull any: Any): Boolean {
        return view === any
    }

    @NonNull
    override fun instantiateItem(@NonNull parent: ViewGroup, position: Int): Any {
        val binding = GalleryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        binding.imageUrl = links[position]
        binding.executePendingBindings()

        parent.addView(binding.image)
        return binding.image
    }

    override fun destroyItem(@NonNull container: ViewGroup, position: Int, @NonNull any: Any) {
        container.removeView(any as ImageView)
    }
}