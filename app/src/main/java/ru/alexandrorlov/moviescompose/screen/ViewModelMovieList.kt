package ru.alexandrorlov.moviescompose.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.alexandrorlov.moviescompose.data.Repository
import ru.alexandrorlov.moviescompose.data.remote.Result
import ru.alexandrorlov.moviescompose.model.ui.Genre
import ru.alexandrorlov.moviescompose.model.ui.Movie
import ru.alexandrorlov.moviescompose.screen.chip.StateChip

class ViewModelMovieList(private val repository: Repository) : ViewModel() {
    private val _state: MutableStateFlow<StateMovieList> = MutableStateFlow(StateMovieList.Loading)
    val state = _state.asStateFlow()

    private val _stateChip: MutableStateFlow<StateChip> = MutableStateFlow(StateChip.Loading)
    val stateChip = _stateChip.asStateFlow()

    private val _stateSearch: MutableStateFlow<String> =
        MutableStateFlow("")
    val stateSearch = _stateSearch.asStateFlow()


    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _state.tryEmit(StateMovieList.Error(exception.message.toString()))
    }

    private var idGenreSelectedList: MutableList<Int> = mutableListOf()
    private var _movieList: List<Movie> = listOf()

    private var getDataJob: Job? = null

    init {
        updateMovie()
        updateChip()
    }

    fun updateMovie() {
        if (getDataJob?.isActive == true) return
        getDataJob?.key

        getDataJob = viewModelScope.launch(coroutineExceptionHandler) {
            val resultMovieList = withContext(Dispatchers.IO) {
                repository.getResultMovieList()
            }
            if (resultMovieList is Result.Success) {
                val stateMovieList: StateMovieList = try {
                    _movieList = resultMovieList.data as List<Movie>
                    StateMovieList.Success(_movieList)
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

    private fun updateChip() {
        viewModelScope.launch(coroutineExceptionHandler) {
            val resultGenreList = withContext(Dispatchers.IO) {
                repository.getResultGenreList()
            }

            if (resultGenreList is Result.Success) {
                val stateChip: StateChip = try {
                    val genreList: List<Genre> = resultGenreList.data as List<Genre>
                    StateChip.Success(genreList)
                } catch (e: Exception) {
                    StateChip.Error(e.message.toString())
                }
                _stateChip.emit(stateChip)
            }

            if (resultGenreList is Result.Error) {
                _stateChip.emit(
                    StateChip.Error(
                        resultGenreList.message
                    )
                )
            }
        }
    }

    fun updateGenreSelectedList(id: Int) {
        if (idGenreSelectedList.contains(id)) {
            idGenreSelectedList.remove(id)
        } else {
            idGenreSelectedList.add(id)
        }
        updateMovieListWhitChips()
    }

    private fun updateMovieListWhitChips() {
        viewModelScope.launch(coroutineExceptionHandler) {
            val movieListFiltered = if (idGenreSelectedList.isNotEmpty()) {
                _movieList.filter { movie ->
                    movie.genreList.any { idGenre ->
                        idGenreSelectedList.contains(idGenre)
                    }
                }
            } else {
                _movieList
            }

            _state.emit(
                StateMovieList.Success(movieListFiltered)
            )
        }
    }

    fun updateMovieListWhitSearch(textSearch: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val regex = Regex(pattern = textSearch.uppercase())
            val movieListSearched = if (textSearch.isNotEmpty()) {
                _movieList.filter { movie ->
                    regex.containsMatchIn(movie.title.uppercase())
                }
            } else {
                _movieList
            }

            _stateSearch.emit(textSearch)
            _state.emit(
                StateMovieList.Success(movieListSearched)
            )
        }
    }
}