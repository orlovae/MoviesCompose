package ru.alexandrorlov.moviescompose.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int = -1,
    val name: String = "error",
    val photo: String = "error",/*можно добавать по умолчанию ссылку на заглушку из drawable*/
    val dateRelease: String = "error",
    val rating: String = "error",
    val ageRating: String = "error",
    val description: String = "error"
) : Parcelable
