package ru.alexandrorlov.moviescompose.model.ui

import ru.alexandrorlov.moviescompose.R
import ru.alexandrorlov.moviescompose.config.ModelConfig.ERROR_INT
import ru.alexandrorlov.moviescompose.config.ModelConfig.ERROR_STRING

data class MovieDetail(
    val id: Int = ERROR_INT,
    val title: String = ERROR_STRING,
    val poster: String  = R.drawable.ic_launcher_error.toString(),
    val backdrop: String = R.drawable.ic_launcher_error.toString(),
    val dateRelease: String = ERROR_STRING,
    val rating: Int = ERROR_INT,
    val ageRating: String = ERROR_STRING,
    val description: String = ERROR_STRING,
    val genreList: List<Genre> = listOf(),
    val actorList: List<Actor> = listOf()
)