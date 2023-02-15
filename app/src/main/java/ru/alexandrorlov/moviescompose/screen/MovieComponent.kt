package ru.alexandrorlov.moviescompose.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.alexandrorlov.moviescompose.model.Movie

@Composable
fun MovieComponent(movie: Movie) {
    Column(
        modifier = Modifier
    ) {
        AsyncImage(
            model = movie.photo,
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
            RatingComponent(starFull = movie.rating.toInt())
            Spacer(modifier = Modifier.weight(1f))
            AgeRatingComponent(ageRating = movie.ageRating)
        }
    }
    
}

@Preview
@Composable
fun MovieComponentPreview() {
    MovieComponent(
        Movie(
            0,
            "Kin-dza-dza",
            "https://upload.wikimedia.org/wikipedia/ru/thumb/9/98/Kin-dza-dza.JPG/240px-Kin-dza-dza.JPG",
            "30.03.1987",
            "3",
            "12+",
            "Прораб Владимир Николаевич Машков и не подозревал, что обычный путь до универсама за хлебом и макаронами обернется межгалактическими путешествиями. А все эта встреча со странным босоногим человеком с каким-то маленьким устройством — «машинкой перемещения», как тот ее назвал. Короче, нажал на кнопку и оказался вместе со случайным попутчиком, студентом в кроличьей шапке, в пустыне, и не в каких-нибудь Каракумах, а на планете-пустыне Плюк в тентуре, галактика Кин-дза-дза в спирали."
        )
    )
}