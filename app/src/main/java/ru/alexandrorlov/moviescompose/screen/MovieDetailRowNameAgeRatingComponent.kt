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
import ru.alexandrorlov.moviescompose.model.Movie

@Composable
fun MovieDetailRowNameAgeRatingComponent(
    movie: Movie
) {
    Row(
        modifier = Modifier
            .padding(
                start = 22.dp,
            ),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = movie.name,
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
        AgeRatingComponent(ageRating = movie.ageRating)

    }

}

@Preview
@Composable
fun MovieDetailRowNameAgeRatingComponentPreview() {
    MovieDetailRowNameAgeRatingComponent(
        movie = Movie(
            0,
            "Kin-dza-dza",
            "https://upload.wikimedia.org/wikipedia/ru/thumb/9/98/Kin-dza-dza.JPG/240px-Kin-dza-dza.JPG",
            "30.03.1987",
            "10.0",
            "12+",
            "Прораб Владимир Николаевич Машков и не подозревал, что обычный путь до универсама за хлебом и макаронами обернется межгалактическими путешествиями. А все эта встреча со странным босоногим человеком с каким-то маленьким устройством — «машинкой перемещения», как тот ее назвал. Короче, нажал на кнопку и оказался вместе со случайным попутчиком, студентом в кроличьей шапке, в пустыне, и не в каких-нибудь Каракумах, а на планете-пустыне Плюк в тентуре, галактика Кин-дза-дза в спирали."
        )
    )
}