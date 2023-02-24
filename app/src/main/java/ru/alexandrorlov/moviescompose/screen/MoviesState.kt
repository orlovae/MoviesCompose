package ru.alexandrorlov.moviescompose.screen

import ru.alexandrorlov.moviescompose.model.Movie

sealed class MoviesState {
    object Loading: MoviesState()
    data class Error(val throwable: Throwable): MoviesState()
    data class Success(val movies: List<Movie>): MoviesState()
}
