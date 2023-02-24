package ru.alexandrorlov.moviescompose.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.alexandrorlov.moviescompose.App

class MoviesViewModel: ViewModel() {
    private val _state: MutableStateFlow<MoviesState> = MutableStateFlow(MoviesState.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            App.movieListFlow.collect { movies ->
                _state.emit(MoviesState.Success(movies))
            }
        }
    }

}