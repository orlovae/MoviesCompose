package ru.alexandrorlov.moviescompose.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.alexandrorlov.moviescompose.model.Movie

@Composable
fun AppNavHost(
    modifier: Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "movieList"
) {
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = startDestination) {
        composable(
            "movieList"
        ) {
            MoviesScreen (
                movies = MoviesViewModel().movies,
                onNavigateToMovieDetail = navController
            )
        }
        composable("movie") {
            MovieDetailScreen(
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
    }
}