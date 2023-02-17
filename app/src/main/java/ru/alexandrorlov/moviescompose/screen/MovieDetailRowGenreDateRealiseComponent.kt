package ru.alexandrorlov.moviescompose.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.alexandrorlov.moviescompose.model.Movie

@Composable
fun MovieDetailRowGenreDateRealiseComponent(
    movie: Movie
) {
    Row(
        modifier = Modifier
            .padding(
                start = 20.dp,
                top = 32.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "боевики",/*movie.genre*/
            fontSize = 10.sp,
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(
                    start = 9.dp,
                    top = 4.dp,
                    end = 9.dp,
                    bottom = 4.dp
                )
        )
        Spacer(modifier = Modifier.width(14.dp))
        Text(
            text = movie.dateRelease,
            fontSize = 12.sp
        )
    }
}

@Preview
@Composable
fun MovieDetailRowGenreDateRealiseComponentPreview() {
    MovieDetailRowGenreDateRealiseComponent(
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