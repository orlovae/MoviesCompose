package ru.alexandrorlov.moviescompose.screen

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import ru.alexandrorlov.moviescompose.App
import ru.alexandrorlov.moviescompose.util.AssetsReader
import ru.alexandrorlov.moviescompose.R
import ru.alexandrorlov.moviescompose.model.Movie
import ru.alexandrorlov.moviescompose.util.Mapper

class MoviesViewModel: ViewModel() {
    private var _movies = App.getListMovie()
    var movies = _movies
}