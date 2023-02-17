package ru.alexandrorlov.moviescompose.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.alexandrorlov.moviescompose.model.Movie

@Composable
fun MoviesScreen(
    movies: List<Movie>
) {
    LazyColumn{
        itemsIndexed(items = movies) { _, item ->
            MovieComponent(movie = item)
        }
    }

}