package ru.alexandrorlov.moviescompose.screen.chip

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.alexandrorlov.moviescompose.model.ui.Genre

@Composable
fun ChipListComponent(
    genreList: List<Genre>,
    onSelected: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .padding(
                start = 20.dp
            )
    ) {
        items(genreList) { genre ->
            ChipComponent(
                genre = genre.name,
                onSelected = { onSelected(genre.id) }
            )
        }
    }
}