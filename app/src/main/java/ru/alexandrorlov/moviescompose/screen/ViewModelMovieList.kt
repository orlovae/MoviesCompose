package ru.alexandrorlov.moviescompose.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.alexandrorlov.moviescompose.data.Repository
import ru.alexandrorlov.moviescompose.data.remote.Result
import ru.alexandrorlov.moviescompose.model.ui.Movie

class ViewModelMovieList(private val repository: Repository) : ViewModel() {
    private val _state: MutableStateFlow<StateMovieList> = MutableStateFlow(StateMovieList.Loading)
    val state = _state.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _state.tryEmit(StateMovieList.Error(exception.message.toString()))
    }

    private var getDataJob: Job? = null

    init {
        update()
    }

    fun update() {
        if (getDataJob?.isActive == true) return

        getDataJob = viewModelScope.launch(coroutineExceptionHandler) {
            val resultMovieList = withContext(Dispatchers.IO) {
                repository.getResultMovieList()
            }
            if (resultMovieList is Result.Success) {
                val stateMovieList: StateMovieList = try {
                    val movieList: List<Movie> = resultMovieList.data as List<Movie>
                    StateMovieList.Success(movieList)
                } catch (e: Exception) {
                    StateMovieList.Error(e.message.toString())
                }
                _state.emit(stateMovieList)
            }

            if (resultMovieList is Result.Error) {
                _state.emit(
                    StateMovieList.Error(
                        resultMovieList.message
                    )
                )
            }
            _isRefreshing.emit(false)
        }
    }
}