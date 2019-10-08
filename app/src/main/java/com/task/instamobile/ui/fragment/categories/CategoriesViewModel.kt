package com.task.instamobile.ui.fragment.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.task.instamobile.model.Category
import com.task.instamobile.ui.activity.CATEGORIES_COLLECTION_REF
import com.task.instamobile.ui.activity.RECIPES_COLLECTION_REF

class CategoriesViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val categories = db.collection(CATEGORIES_COLLECTION_REF)
    private val recipes = db.collection(RECIPES_COLLECTION_REF)

    private val _categories = MutableLiveData<List<Category>>()
    val categoriesLiveData: LiveData<List<Category>>
        get() = _categories

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    init {
        categories.get().addOnSuccessListener {
            val list = mutableListOf<Category>()
            var category: Category
            for (categoryDocument in it.documents) {
                recipes.whereEqualTo("categoryId", categoryDocument.reference.path).get()
                    .addOnSuccessListener { querySnapshot ->
                        category = categoryDocument.toObject(Category::class.java)!!
                        category.categoryRef = categoryDocument.reference.path
                        category.recipesCount = querySnapshot.documents.size
                        list.add(category)
                        list.sortByDescending { category1 ->
                            category1.recipesCount
                        }
                        _categories.value = list
                    }
            }
        }.addOnFailureListener {
            _errorMessage.value = it.localizedMessage
        }
    }

    fun doneShowingErrorMessage() {
        _errorMessage.value = null
    }
}