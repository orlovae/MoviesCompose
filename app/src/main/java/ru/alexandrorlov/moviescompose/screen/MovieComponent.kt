package ru.alexandrorlov.moviescompose.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.alexandrorlov.moviescompose.R
import ru.alexandrorlov.moviescompose.model.Movie

@Composable
fun MovieComponent(
    movie: Movie,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .clickable (
                onClick = onClick
            ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
        ) {
            AsyncImage(
                model = movie.photo,
                placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                error = painterResource(id = R.drawable.ic_launcher_error),
                contentDescription = null,
                modifier = Modifier.aspectRatio(1.0f),
                contentScale = ContentScale.Fit,
            )
            Text(
                text = movie.name,
                modifier = Modifier
                    .padding(
                        start = 3.dp,
                        bottom = 10.dp
                    ),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = movie.description,
                modifier = Modifier
                    .padding(
                        start = 3.dp,
                        end = 3.dp,
                        bottom = 15.dp
                    ),
                fontSize = 12.sp,
                fontFamily = FontFamily.SansSerif,
                maxLines = 6,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RatingComponent(
                    starFull = movie.rating
                )
                Spacer(modifier = Modifier.weight(1f))
                AgeRatingComponent(ageRating = movie.ageRating)
            }
        }
    }


}