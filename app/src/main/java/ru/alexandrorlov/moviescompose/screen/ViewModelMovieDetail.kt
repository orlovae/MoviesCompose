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
import ru.alexandrorlov.moviescompose.data.Repository
import ru.alexandrorlov.moviescompose.model.ui.MovieDetail
import ru.alexandrorlov.moviescompose.data.remote.Result

class ViewModelMovieDetail(
    private val repository: Repository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state: MutableStateFlow<StateMovieDetail> = MutableStateFlow(StateMovieDetail.Loading)
    val state = _state.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler{_, exception ->
        _state.tryEmit(StateMovieDetail.Error(exception.message.toString()))
    }

    init{
        val movieDetailId: String? = savedStateHandle["id"]

        viewModelScope.launch(coroutineExceptionHandler) {
            movieDetailId?.let {
                val resultMovieDetail = withContext(Dispatchers.IO) {
                    repository.getResultMovieDetail(movieDetailId.toInt())
                }
                if (resultMovieDetail is Result.Success) {
                    val stateMovieDetail = try {
                        val movieDetail = resultMovieDetail.data as MovieDetail
                        StateMovieDetail.Success(movieDetail)
                    } catch (e: Exception) {
                        StateMovieDetail.Error(e.message.toString())
                    }
                    _state.emit(stateMovieDetail)
                }
                if (resultMovieDetail is Result.Error) {
                    _state.emit(
                        StateMovieDetail.Error(
                            resultMovieDetail.message
                        )
                    )
                }
            }
        }
    }

    companion object{
        val FACTORY = viewModelFactory {
            initializer {
                val repository = Repository()
                val savedStateHandle = createSavedStateHandle()
                ViewModelMovieDetail(repository, savedStateHandle)
            }
        }
    }
}