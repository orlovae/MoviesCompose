package ru.alexandrorlov.moviescompose.model

import ru.alexandrorlov.moviescompose.R

data class MovieDetail(
    var id: Int = -1,
    val title: String = "error",
    val poster: String  = R.drawable.ic_launcher_error.toString(),
    val backdrop: String = R.drawable.ic_launcher_error.toString(),
    val dateRelease: String = "error",
    val rating: Int = -1,
    val ageRating: String = "error",
    val description: String = "error",
    val genreList: List<Genre> = mutableListOf(),
    val actorList: List<Actor> = mutableListOf()
)