package ru.alexandrorlov.moviescompose.model

import ru.alexandrorlov.moviescompose.R

data class Movie(
    var id: Int = -1,
    val title: String = "error",
    val poster: String  = R.drawable.ic_launcher_error.toString(),
    val rating: Int = -1,
    val ageRating: String = "error",
    val description: String = "error",
)