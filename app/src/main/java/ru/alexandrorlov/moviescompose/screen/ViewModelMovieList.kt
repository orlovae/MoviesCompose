package ru.alexandrorlov.moviescompose.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import ru.alexandrorlov.moviescompose.data.Repository
import ru.alexandrorlov.moviescompose.model.ui.Movie
import ru.alexandrorlov.moviescompose.data.remote.Result

class ViewModelMovieList(private val repository: Repository): ViewModel() {
    private val _state: MutableStateFlow<StateMovieList> = MutableStateFlow(StateMovieList.Loading)
    val state = _state.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()


    private val coroutineExceptionHandler = CoroutineExceptionHandler{_, exception ->
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

    companion object {
        val FACTORY = viewModelFactory {
            initializer {
                val repository = Repository()
                ViewModelMovieList(repository)
            }
        }
    }
}