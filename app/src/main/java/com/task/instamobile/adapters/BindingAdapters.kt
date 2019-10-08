package com.task.instamobile.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
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

@BindingAdapter("app:photosArray")
fun setPhotosArray(viewPager: ViewPager, photos: List<String>?) {
    if (photos != null) {
        viewPager.adapter = SliderAdapter(photos, viewPager.context)
    }
}