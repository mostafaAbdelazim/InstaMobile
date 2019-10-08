package com.task.instamobile.ui.fragment.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private lateinit var viewModel: CategoriesViewModel
    private lateinit var binding: FragmentCategoriesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(CategoriesViewModel::class.java)
        binding = FragmentCategoriesBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@CategoriesFragment
        }
        setObservers()
        return binding.root
    }

    private fun setObservers() {
        viewModel.categoriesLiveData.observe(this, Observer {
            if (it != null) {
                val adapter = CategoriesAdapter(it, object : CategoriesAdapter.MyClickListener {
                    override fun myOnClick(category: Category) {
                        if (category.recipesCount > 0) {
                            view?.findNavController()?.navigate(
                                CategoriesFragmentDirections.actionActionCategoriesToRecipesList(
                                    category
                                )
                            )
                        } else {
                            (activity as MainActivity).showSnackBar(getString(R.string.empty_category_warning_message))
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
    }
}