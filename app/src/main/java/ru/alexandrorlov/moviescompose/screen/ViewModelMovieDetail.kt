package ru.alexandrorlov.moviescompose.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.alexandrorlov.moviescompose.data.Repository
import ru.alexandrorlov.moviescompose.data.remote.Result
import ru.alexandrorlov.moviescompose.model.ui.MovieDetail

class ViewModelMovieDetail @AssistedInject constructor(
    private val repository: Repository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state: MutableStateFlow<StateMovieDetail> =
        MutableStateFlow(StateMovieDetail.Loading)
    val state = _state.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _state.tryEmit(StateMovieDetail.Error(exception.message.toString()))
    }

    init {
        val movieDetailId: Int? = savedStateHandle["id"]

        viewModelScope.launch(coroutineExceptionHandler) {
            movieDetailId?.let {
                val resultMovieDetail = withContext(Dispatchers.IO) {
                    movieDetailId.let { repository.getResultMovieDetail(it) }
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
}