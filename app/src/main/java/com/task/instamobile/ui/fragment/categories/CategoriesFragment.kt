package com.task.instamobile.ui.fragment.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.task.instamobile.R
import com.task.instamobile.adapters.CategoriesAdapter
import com.task.instamobile.databinding.FragmentCategoriesBinding
import com.task.instamobile.model.Category
import com.task.instamobile.ui.activity.MainActivity

class CategoriesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel = ViewModelProviders.of(this).get(CategoriesViewModel::class.java)
        val binding: FragmentCategoriesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_categories, container, false)
        binding.lifecycleOwner = this
        viewModel.categoriesLiveData.observe(this, Observer {
            if (it != null) {
                val adapter = CategoriesAdapter(it, object : CategoriesAdapter.MyClickListener {
                    override fun myOnClick(category: Category) {
                        if (category.recipesCount > 0){
                            view?.findNavController()?.navigate(
                                CategoriesFragmentDirections.actionActionCategoriesToRecipesList(
                                    category
                                )
                            )
                        }else{
                            (activity as MainActivity).showSnackBar("Empty category, Please add more recipes to this category.")
                        }

                    }
                })
                binding.recyclerView.adapter = adapter
            }
        })
        viewModel.errorMessage.observe(this, Observer {
            if (it != null) {
                (activity as MainActivity).showSnackBar(it)
                viewModel.doneShowingErrorMessage()
            }
        })
        return binding.root
    }
}