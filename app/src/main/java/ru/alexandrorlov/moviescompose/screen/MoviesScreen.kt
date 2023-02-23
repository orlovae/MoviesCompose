package ru.alexandrorlov.moviescompose.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ru.alexandrorlov.moviescompose.R

@Composable
fun MoviesScreen(
    moviesViewModel: MoviesViewModel = viewModel(),
    onNavigateToMovieDetail: NavController
) {
    val state = moviesViewModel.state.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (state.value) {
            is MoviesState.Loading -> {
                Box(modifier = Modifier
                    .fillMaxSize()) {
                    CircularProgressAnimated()
                }
            }
            is MoviesState.Error -> {
                Box(modifier = Modifier
                    .fillMaxSize()) {
                    Text(
                        text = stringResource(id = R.string.error),
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        textAlign = TextAlign.Center
                    )
                }
            }
            is MoviesState.Success -> {
                LazyColumn {
                    itemsIndexed(items = (state.value as MoviesState.Success).movies) { _, item ->
                        MovieComponent(
                            movie = item,
                            onClick = {
                                onNavigateToMovieDetail.navigate("movie/${item.id}")
                            }
                        )
                    }
                }
            }
        }
    }
}