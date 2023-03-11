package ru.alexandrorlov.moviescompose.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.alexandrorlov.moviescompose.model.ui.MovieDetail

@Composable
fun ComponentMovieDetailRowNameAgeRating(
    movieDetail: MovieDetail
){
    Row(
        modifier = Modifier
            .padding(
                start = 22.dp,
                end = 20.dp
            ),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = movieDetail.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            maxLines = 1,
            modifier = Modifier
                .padding(
                    top = 14.dp
                )
        )
        Spacer(modifier = Modifier.weight(1f))
        ComponentAgeRating(ageRating = movieDetail.ageRating)
    }
}

@Preview
@Composable
fun ComponentMovieDetailRowNameAgeRatingPreview() {
    ComponentMovieDetailRowNameAgeRating(
        movieDetail = MovieDetail(
            0,
            "Kin-dza-dza",
            "https://upload.wikimedia.org/wikipedia/ru/thumb/9/98/Kin-dza-dza.JPG/240px-Kin-dza-dza.JPG",
            "30.03.1987",
            "30.03.1987",
            4,
            "PG"
        )
    )
}