package com.task.instamobile.ui.fragment.recipesList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.task.instamobile.model.Category
import com.task.instamobile.model.Recipe
import com.task.instamobile.ui.activity.RECIPES_COLLECTION_REF

class RecipesListViewModel(category: Category) : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val recipesRef = db.collection(RECIPES_COLLECTION_REF)
    private val _recipesList = MutableLiveData<List<Recipe>>()
    val recipesList: LiveData<List<Recipe>>
        get() = _recipesList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    init {
        val list = mutableListOf<Recipe>()
        recipesRef.whereEqualTo("categoryId", category.categoryRef)
            .get()
            .addOnSuccessListener {
                for (recipeDocument in it.documents) {
                    list.add(recipeDocument.toObject(Recipe::class.java)!!)
                }
                _recipesList.value = list
            }.addOnFailureListener {
                _errorMessage.value = it.localizedMessage
            }
    }
}