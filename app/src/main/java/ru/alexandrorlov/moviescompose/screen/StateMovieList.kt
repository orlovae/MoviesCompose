package ru.alexandrorlov.moviescompose.screen

import ru.alexandrorlov.moviescompose.model.ui.Movie

sealed class StateMovieList {
    object Loading : StateMovieList()
    data class Error(val message: String) : StateMovieList()
    data class Success(val movieList: List<Movie>) : StateMovieList()
}