package ru.alexandrorlov.moviescompose.screen

import android.util.Log
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
import ru.alexandrorlov.moviescompose.data.remote.RepositoryRemote
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
        Log.d("OAE", "ViewModelMovieDetail movieDetailId = $movieDetailId")

        viewModelScope.launch(coroutineExceptionHandler) {
            movieDetailId?.let {
                val resultMovieDetailFromNetwork = withContext(Dispatchers.IO) {
                    repository.getResultMovieDetails(movieDetailId.toInt())
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
                val repository = Repository()
                val savedStateHandle = createSavedStateHandle()
                ViewModelMovieDetail(repository, savedStateHandle)
            }
        }
    }
}