package ru.alexandrorlov.moviescompose.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.alexandrorlov.moviescompose.model.Movie
import ru.alexandrorlov.moviescompose.network.RepositoryNetwork
import ru.alexandrorlov.moviescompose.network.Result

class ViewModelMovieList(private val repositoryNetwork: RepositoryNetwork): ViewModel() {
    private val _state: MutableStateFlow<StateMovieList> = MutableStateFlow(StateMovieList.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val resultMovieListFromNetwork = withContext(Dispatchers.IO) {
                repositoryNetwork.getResultListMovieFromNetwork()
            }
            if (resultMovieListFromNetwork is Result.Success) {
                val movieList: List<Movie> = resultMovieListFromNetwork.data as List<Movie>
                _state.emit(StateMovieList.Success(movieList))
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
                val repositoryNetwork = RepositoryNetwork
                ViewModelMovieList(repositoryNetwork)
            }
        }
    }
}