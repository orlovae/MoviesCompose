package ru.alexandrorlov.moviescompose.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.alexandrorlov.moviescompose.App
import ru.alexandrorlov.moviescompose.R

class MovieDetailViewModel: ViewModel() {

    private val _state: MutableStateFlow<MovieState> = MutableStateFlow(MovieState.Loading)
    val state = _state.asStateFlow()

    fun onPressItemMovieScreen(id: Int?) {
        viewModelScope.launch {
            App.getMovie(id).collect{movie ->
                movie?.let {
                    _state.emit(MovieState.Success(movie))
                } ?: run {
                    _state.emit(MovieState.Error(R.string.error_movie_detail))
                }
            }
        }
    }
}