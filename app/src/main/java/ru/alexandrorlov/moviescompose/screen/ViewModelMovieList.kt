package ru.alexandrorlov.moviescompose.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import ru.alexandrorlov.moviescompose.model.Movie
import ru.alexandrorlov.moviescompose.network.RepositoryRemote
import ru.alexandrorlov.moviescompose.network.Result

class ViewModelMovieList(private val repositoryRemote: RepositoryRemote): ViewModel() {
    private val _state: MutableStateFlow<StateMovieList> = MutableStateFlow(StateMovieList.Loading)
    val state = _state.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler{_, exception ->
        _state.tryEmit(StateMovieList.Error(exception.message.toString()))
    }
    init {
        viewModelScope.launch(coroutineExceptionHandler) {
            val resultMovieListFromNetwork = withContext(Dispatchers.IO) {
                repositoryRemote.getResultListMovieFromNetwork()
            }

            if (resultMovieListFromNetwork is Result.Success) {
                val stateMovieList: StateMovieList = try {
                    val movieList: List<Movie> = resultMovieListFromNetwork.data as List<Movie>
                    StateMovieList.Success(movieList)
                } catch (e: Exception) {
                    StateMovieList.Error(e.message.toString())
                }
                _state.emit(stateMovieList)
            }

            if (resultMovieListFromNetwork is Result.Error) {
                _state.emit(StateMovieList.Error(
                    resultMovieListFromNetwork.message
                ))
            }
        }
    }

    companion object {
        val FACTORY = viewModelFactory {
            initializer {
                val repositoryRemote = RepositoryRemote
                ViewModelMovieList(repositoryRemote)
            }
        }
    }
}