package ru.alexandrorlov.moviescompose.screen.chip

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.alexandrorlov.moviescompose.R
import ru.alexandrorlov.moviescompose.screen.ComponentStateLoading
import ru.alexandrorlov.moviescompose.screen.ViewModelMovieList
import ru.alexandrorlov.moviescompose.screen.search.ComponentStateError

@Composable
fun ChipListStateComponent(
    stateChip: State<StateChip>,
    viewModel: ViewModelMovieList
) {
    Box(
        modifier = Modifier
            .height(62.dp)
    ) {
        when (stateChip.value) {
            is StateChip.Loading -> {
                ComponentStateLoading()
            }
            is StateChip.Error -> {
                ComponentStateError(
                    messageError = (stateChip.value as StateChip.Error).message
                )
            }
            is StateChip.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        text = stringResource(id = R.string.popular_now),
                        modifier = Modifier
                            .padding(
                                start = 20.dp,
                                bottom = 8.dp
                            ),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        textAlign = TextAlign.Start
                    )
                    ChipListComponent(
                        genreList = (stateChip.value as StateChip.Success).genreList,
                        onSelected = { id ->
                            viewModel.updateGenreSelectedList(id)
                        }
                    )
                }
            }
        }
    }
}