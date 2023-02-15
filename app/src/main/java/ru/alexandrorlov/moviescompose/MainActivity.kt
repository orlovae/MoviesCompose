package ru.alexandrorlov.moviescompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.alexandrorlov.moviescompose.model.Movie
import ru.alexandrorlov.moviescompose.screen.MovieComponent
import ru.alexandrorlov.moviescompose.screen.MoviesScreen
import ru.alexandrorlov.moviescompose.screen.MoviesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesScreen(
                moviesViewModel = MoviesViewModel()            
            )
            MovieComponent(movie = Movie(
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
    }
}

