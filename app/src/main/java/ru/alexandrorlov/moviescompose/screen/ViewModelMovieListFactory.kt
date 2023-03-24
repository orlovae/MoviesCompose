package ru.alexandrorlov.moviescompose.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.alexandrorlov.moviescompose.data.Repository
import javax.inject.Inject

class ViewModelMovieListFactory @Inject constructor(private val repository: Repository) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModelMovieList(repository) as T
    }
}