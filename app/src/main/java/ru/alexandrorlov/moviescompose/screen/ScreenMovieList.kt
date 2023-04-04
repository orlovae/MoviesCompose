package ru.alexandrorlov.moviescompose.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.alexandrorlov.moviescompose.screen.chip.ChipListStateComponent
import ru.alexandrorlov.moviescompose.screen.search.ComponentStateError
import ru.alexandrorlov.moviescompose.screen.search.SearchComponent

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScreenMovieList(
    viewModel: ViewModelMovieList,
    onMovieClick: (Int) -> Unit
) {
    val state = viewModel.state.collectAsState()
    val stateChip = viewModel.stateChip.collectAsState()
    val isRefreshing = viewModel.isRefreshing.collectAsState()
    val refreshScope = rememberCoroutineScope()

    fun refresh() = refreshScope.launch {
        viewModel.updateMovie()
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing.value,
        onRefresh = ::refresh
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (state.value) {
            is StateMovieList.Loading -> {
                ComponentStateLoading()
            }
            is StateMovieList.Error -> {
                ComponentStateError(
                    messageError = (state.value as StateMovieList.Error).message
                )
            }
            is StateMovieList.Success -> {
                Column() {
                    SearchComponent(
                        viewModel = viewModel
                    )
                    ChipListStateComponent(
                        stateChip = stateChip,
                        viewModel = viewModel
                    )
                    Box(
                        modifier = Modifier
                            .pullRefresh(pullRefreshState)
                    ) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(
                                horizontal = 20.dp,
                                vertical = 20.dp
                            ),
                            verticalArrangement = Arrangement.spacedBy(20.dp),
                            horizontalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            items((state.value as StateMovieList.Success).movieList) { item ->
                                ComponentMovie(
                                    movie = item,
                                    onClick = onMovieClick
                                )
                            }
                        }
                        PullRefreshIndicator(
                            refreshing = isRefreshing.value,
                            state = pullRefreshState,
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                        )
                    }
                }
            }
        }
    }
}