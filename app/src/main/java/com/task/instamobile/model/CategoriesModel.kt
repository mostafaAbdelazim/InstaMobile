package com.task.instamobile.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
    val id: Int = -1,
    val name: String = "",
    val photo_url: String = "",
    @Expose var recipesCount: Int = 0,
    @Expose var categoryRef: String = ""
) : Parcelable