package com.task.instamobile.ui.fragment.categories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.task.instamobile.model.Categories
import com.task.instamobile.model.Category
import com.task.instamobile.model.RecipesList
import com.task.instamobile.ui.activity.CATEGORIES_COLLECTION_REF
import com.task.instamobile.ui.activity.CATEGORIES_DOCUMENT_REF
import com.task.instamobile.ui.activity.RECIPES_COLLECTION_REF
import com.task.instamobile.ui.activity.RECIPES_DOCUMENT_REF

class CategoriesViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val categories =
        db.collection(CATEGORIES_COLLECTION_REF).document(CATEGORIES_DOCUMENT_REF)
    private val recipes = db.collection(RECIPES_COLLECTION_REF).document(RECIPES_DOCUMENT_REF)

    private val _categories = MutableLiveData<Categories>()
    val categoriesLiveData: LiveData<Categories>
        get() = _categories

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    init {
        recipes.get().addOnSuccessListener {
            val rec = it.toObject(RecipesList::class.java)
            rec!!.RecipesList[0].categoryId?.get()?.addOnSuccessListener { documentSnapshot ->
                Log.e("test", documentSnapshot.toObject(Category::class.java)?.name)
            }
            Log.e("test", rec.RecipesList.toString())
        }
//        categories.get().addOnSuccessListener {
//            recipes.get().addOnSuccessListener { recipes ->
//                recipesList = recipes.toObject(RecipesList::class.java)!!
//                Log.e("sizebefore", recipesList.RecipesList.size.toString())
//                categoriesList = it.toObject(Categories::class.java)!!
//                for (category in categoriesList.CategoriesList) {
//                    val size =
//                        recipesList.RecipesList.filter { recipe -> category.id == recipe.categoryId }
//                            .size
//                    Log.e("sizeafter", size.toString())
//                    category.recipesCount = size
//                }
//                _categories.value = categoriesList
//            }
//        }.addOnFailureListener {
//            _errorMessage.value = it.localizedMessage
//        }
    }

    fun doneShowingErrorMessage() {
        _errorMessage.value = null
    }
}