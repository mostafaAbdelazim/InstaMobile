package com.task.instamobile.model


data class Categories(val CategoriesList: List<Category> = emptyList())

data class Category(
    val id: Int = -1,
    val name: String = "",
    val photo_url: String = ""
)