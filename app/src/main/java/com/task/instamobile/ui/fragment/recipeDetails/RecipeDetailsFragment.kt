package com.task.instamobile.ui.fragment.recipeDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
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
        binding = FragmentRecipeDetailsBinding.inflate(
            layoutInflater,
            container,
            false
        ).apply {
            viewModel = recipeDetailsViewModel
            recipe = args.recipe
            lifecycleOwner = this@RecipeDetailsFragment
        }
        binding.indicator.setupWithViewPager(binding.pager)
        return binding.root
    }
}