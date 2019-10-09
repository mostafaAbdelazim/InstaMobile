package com.task.instamobile.ui.fragment.recipesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.task.instamobile.adapters.HomeAdapter
import com.task.instamobile.adapters.RecipesListAdapter
import com.task.instamobile.databinding.FragmentRecipesListBinding
import com.task.instamobile.model.Recipe
import com.task.instamobile.ui.activity.MainActivity

class RecipesList : Fragment() {
    private val args by navArgs<RecipesListArgs>()
    private val recipesListViewModel: RecipesListViewModel by viewModels {
        RecipesListViewModelFactory(args.selectedCategory)
    }
    private lateinit var binding: FragmentRecipesListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipesListBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@RecipesList
        }
        setObservers()
        return binding.root
    }

    private fun setObservers() {
        recipesListViewModel.recipesList.observe(this, Observer {
            val adapter = RecipesListAdapter(it, object : HomeAdapter.MyClickListener {
                override fun myOnClick(recipe: Recipe) {
                    view?.findNavController()?.navigate(
                        RecipesListDirections.actionRecipesListToRecipeDetailsFragment(recipe)
                    )
                }
            })
            binding.recyclerView.adapter = adapter
        })
        recipesListViewModel.errorMessage.observe(this, Observer {
            (activity as MainActivity).showSnackBar(it)
        })
    }
}