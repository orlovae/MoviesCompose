package ru.alexandrorlov.moviescompose.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import ru.alexandrorlov.moviescompose.R

@Composable
fun MovieDetailScreen(
    movieDetailViewModel: MovieDetailViewModel = viewModel(),
    id: Int?
) {
    movieDetailViewModel.onPressItemMovieScreen(id)
    val state = movieDetailViewModel.state.collectAsState()
    Box(
        modifier = Modifier
        .fillMaxSize()
    ) {
        when (state.value) {
            is MovieState.Loading -> {
                Box(modifier = Modifier
                    .fillMaxSize()) {
                    CircularProgressAnimated()
                }
            }
            is MovieState.Error -> {
                Box(modifier = Modifier
                    .fillMaxSize()) {
                    Text(
                        text = stringResource(id = (state.value as MovieState.Error).message),
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        textAlign = TextAlign.Center
                    )
                }
            }
            is MovieState.Success -> {
                val movie = (state.value as MovieState.Success).movie
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    AsyncImage(
                        model = movie.photo,
                        placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                        error = painterResource(id = R.drawable.ic_launcher_error),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop

                    )
                    Card(
                        modifier = Modifier
                            .fillMaxHeight(0.75f),

                        shape = RoundedCornerShape(30.dp)

                    ) {
                        Column(
                            modifier = Modifier
                        ) {
                            MovieDetailRowGenreDateRealiseComponent(movie = movie)
                            MovieDetailRowNameAgeRatingComponent(movie = movie)
                            Box(
                                modifier = Modifier
                                    .padding(
                                        start = 22.dp,
                                        top = 8.dp
                                    )
                            ) {
                                RatingComponent(starFull = movie.rating)
                            }
                            Text(
                                text = movie.description,
                                fontSize = 12.sp,
                                fontFamily = FontFamily.SansSerif,
                                modifier = Modifier.padding(
                                    start = 20.dp,
                                    top = 20.dp,
                                    end = 20.dp
                                )
                            )
                            Text(
                                text = stringResource(id = R.string.actor),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.SansSerif,
                                modifier = Modifier
                                    .padding(
                                        start = 22.dp,
                                        top = 35.dp
                                    )
                            )
                            //TODO здесь будет Компонент на вход принимающий лист с актёрами
                        }
                    }
                }
            }
        }
    }
}