package com.task.instamobile.model

import com.google.gson.annotations.Expose

data class Category(
    val id: Int = -1,
    val name: String = "",
    val photo_url: String = "",
    @Expose var recipesCount: Int = 0
)