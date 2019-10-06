package com.task.instamobile.model

import com.google.firebase.firestore.DocumentReference

data class RecipesList(val RecipesList: List<Recipe> = emptyList())

data class Recipe(
    val description: String = "",
    val ingredientsList: List<Ingredient> = emptyList(),
    val photo_url: String = "",
    val photosArray: List<String> = emptyList(),
    val time: String = "",
    val title: String = "",
    val categoryId: DocumentReference? = null ,
    val recipeId: Int = -1
)

data class Ingredient(val id: String = "", val amount: String = "")
