package ru.alexandrorlov.moviescompose.screen.chip

import ru.alexandrorlov.moviescompose.model.ui.Genre

sealed class StateChip {
    object Loading : StateChip()
    data class Error(val message: String) : StateChip()
    data class Success(val genreList: List<Genre>) : StateChip()
}