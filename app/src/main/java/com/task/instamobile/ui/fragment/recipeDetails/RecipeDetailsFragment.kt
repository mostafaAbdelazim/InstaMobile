package com.task.instamobile.ui.fragment.recipeDetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.task.instamobile.R
import com.task.instamobile.adapters.SliderAdapter
import com.task.instamobile.databinding.FragmentRecipeDetailsBinding

class RecipeDetailsFragment : Fragment() {
    private val args by navArgs<RecipeDetailsFragmentArgs>()
    private val recipeDetailsViewModel by viewModels<RecipeDetailsViewModel> {
        RecipeDetailsViewModelFactory(args.recipe)
    }
    private lateinit var binding: FragmentRecipeDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_recipe_details,
            container,
            false
        )
        binding.viewModel = recipeDetailsViewModel
        binding.recipe = args.recipe
        binding.lifecycleOwner = this
        Log.e("test", args.recipe.photosArray.toString())
        binding.pager.adapter = SliderAdapter(args.recipe.photosArray, layoutInflater)
        return binding.root
    }
}