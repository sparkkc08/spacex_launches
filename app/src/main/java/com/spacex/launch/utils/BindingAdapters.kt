package com.spacex.launch.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.spacex.launch.R
import java.text.DateFormat
import java.util.*

@BindingAdapter("divider")
fun setRecycleDivider(recyclerView: RecyclerView, divider: Boolean) {
    if (divider) {
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )
    }
}

@BindingAdapter("date", "format")
fun setDate(textView: TextView, date: Date?, format: DateFormat) {
    date?.run {
        textView.text = format.format(this)
    }
}

@BindingAdapter("goneIfEmpty")
fun setGoneIfEmpty(textView: TextView, text: String?) {
    textView.visibility = if (text.isNullOrEmpty()) View.GONE else View.VISIBLE
}

@BindingAdapter("imageUrl")
fun setImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .placeholder(R.drawable.progress_animated)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}