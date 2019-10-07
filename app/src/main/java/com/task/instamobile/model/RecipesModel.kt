package com.task.instamobile.model

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.DocumentReference
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Recipe(
    val description: String = "",
    val ingredientsList: List<Ingredient> = emptyList(),
    val photo_url: String = "",
    val photosArray: List<String> = emptyList(),
    val time: String = "",
    val title: String = "",
    var categoryId: @RawValue DocumentReference? = null,
    val recipeId: Int = -1,
    @Expose var categoryName: String = ""
) : Parcelable {
    private constructor(parcel: Parcel) : this(
        categoryId = parcel.readValue(DocumentReference::class.java.classLoader) as DocumentReference
    )

    companion object : Parceler<Recipe> {
        override fun create(parcel: Parcel): Recipe {
            return Recipe(parcel)
        }

        override fun newArray(size: Int): Array<Recipe> {
            return super.newArray(size)
        }

        override fun Recipe.write(parcel: Parcel, flags: Int) {
            parcel.writeValue(categoryId)
        }
    }
}

@Parcelize
data class Ingredient(val id: String = "", val amount: String = "") : Parcelable