package ru.alexandrorlov.moviescompose.screen

import ru.alexandrorlov.moviescompose.model.Movie

sealed class MovieState {
    object Loading: MovieState()
    data class Error(val message: Int): MovieState()
    data class Success(val movie: Movie): MovieState()
}