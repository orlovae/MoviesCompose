package ru.alexandrorlov.moviescompose.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import ru.alexandrorlov.moviescompose.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScreenMovieList(
    viewModel: ViewModelMovieList,
    onMovieClick: (Int) -> Unit
) {
    val state = viewModel.state.collectAsState()
    val isRefreshing = viewModel.isRefreshing.collectAsState()
    val refreshScope = rememberCoroutineScope()

    fun refresh() = refreshScope.launch {
        viewModel.update()
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
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    ComponentCircularProgressAnimated()
                }
            }
            is StateMovieList.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        text = stringResource(R.string.error) +
                                System.lineSeparator() +
                                (state.value as StateMovieList.Error).message,
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        textAlign = TextAlign.Center
                    )
                }
            }
            is StateMovieList.Success -> {
                Box(
                    modifier = Modifier
                        .padding()
                        .pullRefresh(pullRefreshState)
                ) {
                    LazyColumn {
                        items(items = (state.value as StateMovieList.Success).movieList) { item ->
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