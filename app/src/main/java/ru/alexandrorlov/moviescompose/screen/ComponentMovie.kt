package ru.alexandrorlov.moviescompose.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.alexandrorlov.moviescompose.R
import ru.alexandrorlov.moviescompose.config.ModelConfig
import ru.alexandrorlov.moviescompose.model.ui.Movie


@Composable
fun ComponentMovie(
    movie: Movie,
    onClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .clickable {
                onClick(movie.id)
            },
        shape = RoundedCornerShape(15.dp)
    ) {
        Column {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(movie.poster)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                error = painterResource(id = R.drawable.ic_launcher_error),
                contentDescription = null,
                modifier = Modifier
                    .height(216.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(15.dp)),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = movie.title,
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
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(
                        start = 3.dp,
                        end = 3.dp
                    )
            ) {
                ComponentRating(
                    starsMovie = movie.rating
                )
                Spacer(modifier = Modifier.weight(1f))
                ComponentAgeRating(ageRating = movie.ageRating)
            }
        }
    }
}

@Preview
@Composable
fun ComponentMoviePreview() {
    ComponentMovie(
        movie = Movie(
            id = 1,
            title = "Kin-dza-dza",
            poster = "https://upload.wikimedia.org/wikipedia/ru/thumb/9/98/Kin-dza-dza.JPG/240px-Kin-dza-dza.JPG",
            rating = 4,
            ageRating = ModelConfig.ERROR_STRING,
            description = "Прораб Владимир Николаевич Машков и не подозревал, что обычный путь до универсама за хлебом и макаронами обернется межгалактическими путешествиями. А все эта встреча со странным босоногим человеком с каким-то маленьким устройством — «машинкой перемещения», как тот ее назвал. Короче, нажал на кнопку и оказался вместе со случайным попутчиком, студентом в кроличьей шапке, в пустыне, и не в каких-нибудь Каракумах, а на планете-пустыне Плюк в тентуре, галактика Кин-дза-дза в спирали.",
            dateRelease = "30.03.1987",
            genreList = listOf(12, 35, 18)
        ),
        onClick = {}
    )
}