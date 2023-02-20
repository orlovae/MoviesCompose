package ru.alexandrorlov.moviescompose.model

import ru.alexandrorlov.moviescompose.R

data class Movie(
    val id: Int = -1,
    val name: String = "error",
    val photo: String  = R.drawable.ic_launcher_error.toString(),
    val dateRelease: String = "error",
    val rating: Int = -1,
    val ageRating: String = "error",
    val description: String = "error"
)