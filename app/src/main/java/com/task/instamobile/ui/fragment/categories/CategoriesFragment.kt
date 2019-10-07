package com.task.instamobile.ui.fragment.categories


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.task.instamobile.R
import com.task.instamobile.databinding.FragmentCategoriesBinding
import com.task.instamobile.ui.activity.MainActivity

class CategoriesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel = ViewModelProviders.of(this).get(CategoriesViewModel::class.java)
        val binding: FragmentCategoriesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_categories, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.errorMessage.observe(this, Observer {
            if (it != null) {
                (activity as MainActivity).showSnackBar(it)
                viewModel.doneShowingErrorMessage()
            }
        })
        return binding.root
    }
}