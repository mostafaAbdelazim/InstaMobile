package com.task.instamobile.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.task.instamobile.R
import com.task.instamobile.adapters.CategoriesAdapter
import com.task.instamobile.model.Category

@BindingAdapter("app:imageUrl")
fun setImage(imageView: ImageView, url: String?) {
    if (url != null) {
        Glide.with(imageView.context).setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_placeholder).error(R.drawable.ic_broken_image)
        ).load(url).into(imageView)
    }
}

@BindingAdapter("app:recyclerAdapter")
fun setAdapter(recyclerView: RecyclerView, data: List<Category>?) {
    if (data != null) {
        val adapter = CategoriesAdapter(data)
        recyclerView.adapter = adapter
    }
}