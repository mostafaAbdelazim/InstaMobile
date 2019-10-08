package com.task.instamobile.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.task.instamobile.model.Category
import com.task.instamobile.model.Recipe
import com.task.instamobile.ui.activity.RECIPES_COLLECTION_REF

class HomeViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val recipesRef = db.collection(RECIPES_COLLECTION_REF)

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>>
        get() = _recipes

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage
    init {
        recipesRef.get().addOnSuccessListener {
            val list = mutableListOf<Recipe>()
            var recipe: Recipe
            for (recipeDocument in it.documents) {
                db.document(recipeDocument.toObject(Recipe::class.java)?.categoryId!!)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->
                        val category: Category = documentSnapshot.toObject(Category::class.java)!!
                        recipe = recipeDocument.toObject(Recipe::class.java)!!
                        recipe.categoryName = category.name
                        list.add(recipe)
                        _recipes.value = list
                    }.addOnFailureListener { exception ->
                        _errorMessage.value = exception.localizedMessage
                    }
            }
        }
    }

    fun doneShowingErrorMessage() {
        _errorMessage.value = null
    }
}