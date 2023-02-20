package ru.alexandrorlov.moviescompose.screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ru.alexandrorlov.moviescompose.App
import ru.alexandrorlov.moviescompose.model.Movie
import ru.alexandrorlov.moviescompose.util.Mapper

class MovieDetailViewModel: ViewModel() {
    private val _movie = Movie()
    val movie = _movie

    fun onPressItemMovieScreen(id: Int?): Movie {
        return id?.let { App.getMovie(it) } ?: Movie()
    }
}