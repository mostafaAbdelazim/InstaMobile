package com.task.instamobile.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.task.instamobile.R

@BindingAdapter("app:imageUrl")
fun setImage(imageView: ImageView, url: String?) {
    if (url != null) {
        Glide.with(imageView.context).setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_placeholder).error(R.drawable.ic_broken_image)
        ).load(url).into(imageView)
    }
}