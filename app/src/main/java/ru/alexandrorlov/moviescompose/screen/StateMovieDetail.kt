package ru.alexandrorlov.moviescompose.screen

import ru.alexandrorlov.moviescompose.model.ui.MovieDetail

sealed class StateMovieDetail {
    object Loading: StateMovieDetail()
    data class Error(val message: String): StateMovieDetail()
    data class Success(val movie: MovieDetail): StateMovieDetail()
}