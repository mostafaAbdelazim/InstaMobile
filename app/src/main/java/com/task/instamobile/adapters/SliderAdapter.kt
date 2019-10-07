package com.task.instamobile.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.task.instamobile.R
import com.task.instamobile.databinding.ImageSlideBinding


class SliderAdapter(private val photos: List<String>, private val layoutInflater: LayoutInflater) :
    PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding: ImageSlideBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.image_slide, container, false)
        binding.url = photos[position]
        return binding.root
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return photos.size
    }
}