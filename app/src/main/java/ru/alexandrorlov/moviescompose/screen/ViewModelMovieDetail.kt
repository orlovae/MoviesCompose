package ru.alexandrorlov.moviescompose.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.alexandrorlov.moviescompose.model.MovieDetail
import ru.alexandrorlov.moviescompose.network.RepositoryRemote
import ru.alexandrorlov.moviescompose.network.Result

class ViewModelMovieDetail(
    private val repositoryRemote: RepositoryRemote,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state: MutableStateFlow<StateMovieDetail> = MutableStateFlow(StateMovieDetail.Loading)
    val state = _state.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler{_, exception ->
        _state.tryEmit(StateMovieDetail.Error(exception.message.toString()))
    }

    init{
        val movieDetailId: String? = savedStateHandle["id"]

        viewModelScope.launch {
            movieDetailId?.let {
                val resultMovieDetailFromNetwork = withContext(Dispatchers.IO) {
                    repositoryRemote.getResultMovieDetailsNetwork(movieDetailId.toInt())
                }
                if (resultMovieDetailFromNetwork is Result.Success) {
                    val stateMovieDetail = try {
                        val movieDetail = resultMovieDetailFromNetwork.data as MovieDetail
                        StateMovieDetail.Success(movieDetail)
                    } catch (e: Exception) {
                        StateMovieDetail.Error(e.message.toString())
                    }
                    _state.emit(stateMovieDetail)
                }
                if (resultMovieDetailFromNetwork is Result.Error) {
                    _state.emit(
                        StateMovieDetail.Error(
                            resultMovieDetailFromNetwork.message
                        )
                    )
                }
            }
        }
    }

    companion object{
        val FACTORY = viewModelFactory {
            initializer {
                val repositoryRemote = RepositoryRemote
                val savedStateHandle = createSavedStateHandle()
                ViewModelMovieDetail(repositoryRemote, savedStateHandle)
            }
        }
    }
}