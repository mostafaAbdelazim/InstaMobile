package com.task.instamobile.ui.fragment.recipesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.task.instamobile.model.Category

class RecipesListViewModelFactory(val category: Category) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecipesListViewModel(category) as T
    }
}