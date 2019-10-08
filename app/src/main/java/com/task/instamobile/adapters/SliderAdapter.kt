package com.task.instamobile.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.task.instamobile.databinding.ImageSlideBinding


class SliderAdapter(private val photos: List<String>, private val context: Context) :
    PagerAdapter() {
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {


        val binding =
            ImageSlideBinding.inflate(
                LayoutInflater.from(context),
                container,
                false
            )
        binding.imageUrl = photos[position]
        container.addView(binding.root)
        return binding.root
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return photos.size
    }
}