package ru.alexandrorlov.moviescompose.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.alexandrorlov.moviescompose.model.MovieDetail
import ru.alexandrorlov.moviescompose.network.RepositoryNetwork
import ru.alexandrorlov.moviescompose.network.Result

class ViewModelMovieDetail(
    private val repositoryNetwork: RepositoryNetwork,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state: MutableStateFlow<StateMovieDetail> = MutableStateFlow(StateMovieDetail.Loading)
    val state = _state.asStateFlow()

    init{
        val movieDetailId: String = checkNotNull(savedStateHandle["id"])

        viewModelScope.launch {
            val resultMovieDetailFromNetwork = withContext(Dispatchers.IO) {
                repositoryNetwork.getResultMovieDetailsNetwork(movieDetailId.toInt())
            }
            if (resultMovieDetailFromNetwork is Result.Success) {
                val movieDetail = resultMovieDetailFromNetwork.data as MovieDetail
                _state.emit(StateMovieDetail.Success(movieDetail))
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

    companion object{
        val FACTORY = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                val repositoryNetwork = RepositoryNetwork
                ViewModelMovieDetail(repositoryNetwork, savedStateHandle)
            }
        }
    }
}