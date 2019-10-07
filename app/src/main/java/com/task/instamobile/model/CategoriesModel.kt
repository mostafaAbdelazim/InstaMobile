package com.task.instamobile.model

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.DocumentReference
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Category(
    val id: Int = -1,
    val name: String = "",
    val photo_url: String = "",
    @Expose var recipesCount: Int = 0,
    @Expose var categoryRef: @RawValue DocumentReference? = null
) : Parcelable {
    private constructor(parcel: Parcel) : this(
        categoryRef = parcel.readValue(DocumentReference::class.java.classLoader) as DocumentReference
    )

    companion object : Parceler<Category> {
        override fun create(parcel: Parcel): Category {
            return Category(parcel)
        }

        override fun newArray(size: Int): Array<Category> {
            return super.newArray(size)
        }

        override fun Category.write(parcel: Parcel, flags: Int) {
            parcel.writeValue(categoryRef)
        }
    }
}