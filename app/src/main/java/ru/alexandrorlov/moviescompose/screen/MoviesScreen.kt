package ru.alexandrorlov.moviescompose.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ru.alexandrorlov.moviescompose.model.Movie

@Composable
fun MoviesScreen(
    movies: List<Movie>,//TODO задесь должна быть вьюмодель
    onNavigateToMovieDetail: NavController
) {
    LazyColumn{
        itemsIndexed(items = movies) { _, item ->
            MovieComponent(
                movie = item,
                onClick = {
                    onNavigateToMovieDetail.navigate("movie/${item.id}")
                }
            )
        }
    }
}