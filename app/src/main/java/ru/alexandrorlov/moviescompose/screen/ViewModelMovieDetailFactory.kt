package ru.alexandrorlov.moviescompose.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.AssistedFactory

class ViewModelMovieDetailFactory(
    private val factory: ViewModelMovieDetailAssistedFactory,
    private val savedStateHandle: SavedStateHandle
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return factory.create(savedStateHandle) as T
    }
}

@AssistedFactory
interface ViewModelMovieDetailAssistedFactory {
    fun create(savedStateHandle: SavedStateHandle): ViewModelMovieDetail
}