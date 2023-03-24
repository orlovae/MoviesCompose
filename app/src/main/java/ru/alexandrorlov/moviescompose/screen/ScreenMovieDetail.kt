package ru.alexandrorlov.moviescompose.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
fun ScreenMovieDetail(
    viewModel: ViewModelMovieDetail
) {
    val state = viewModel.state.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (state.value) {
            is StateMovieDetail.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    ComponentCircularProgressAnimated()
                }
            }
            is StateMovieDetail.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        text = stringResource(R.string.error) +
                                System.lineSeparator() +
                                (state.value as StateMovieDetail.Error).message,
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        textAlign = TextAlign.Center
                    )
                }
            }
            is StateMovieDetail.Success -> {
                val movieDetail = (state.value as StateMovieDetail.Success).movie
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    AsyncImage(
                        model = movieDetail.poster,
                        placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                        error = painterResource(id = R.drawable.ic_launcher_error),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop

                    )
                    Card(
                        modifier = Modifier
                            .fillMaxHeight(0.75f)
                            .verticalScroll(rememberScrollState()),
                        shape = RoundedCornerShape(30.dp)

                    ) {
                        Column {
                            ComponentMovieDetailRowGenreDateRealise(movieDetail = movieDetail)
                            ComponentMovieDetailRowNameAgeRating(movieDetail = movieDetail)
                            Box(
                                modifier = Modifier
                                    .padding(
                                        start = 22.dp,
                                        top = 8.dp
                                    )
                            ) {
                                ComponentRating(starsMovie = movieDetail.rating)
                            }
                            Text(
                                text = movieDetail.description,
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
                            ComponentActorList(
                                actorList = movieDetail.actorList
                            )
                        }
                    }
                }
            }
        }
    }
}