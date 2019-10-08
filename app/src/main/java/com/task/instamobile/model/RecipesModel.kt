package com.task.instamobile.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe(
    val description: String = "",
    val ingredientsList: List<String> = emptyList(),
    val photo_url: String = "",
    val photosArray: List<String> = emptyList(),
    val time: String = "",
    val title: String = "",
    var categoryId: String = "",
    val recipeId: Int = -1,
    @Expose var categoryName: String = ""
) : Parcelable

@Parcelize
data class Ingredient(val id: String = "", val amount: String = "") : Parcelable