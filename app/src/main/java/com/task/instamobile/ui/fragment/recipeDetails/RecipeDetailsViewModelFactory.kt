package com.task.instamobile.ui.fragment.recipeDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.task.instamobile.model.Recipe

class RecipeDetailsViewModelFactory(val recipe: Recipe) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecipeDetailsViewModel(recipe) as T
    }
}