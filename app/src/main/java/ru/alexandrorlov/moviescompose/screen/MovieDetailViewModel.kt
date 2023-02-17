package ru.alexandrorlov.moviescompose.screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ru.alexandrorlov.moviescompose.model.Movie

class MovieDetailViewModel: ViewModel() {
    private val _movie = mutableStateOf(Movie)
    val movie: MutableState<Movie.CREATOR> = _movie

    private val movies: List<Movie>

    init {
        movies = mutableStateListOf<Movie>(
            Movie(
                0,
                "Kin-dza-dza",
                "https://upload.wikimedia.org/wikipedia/ru/thumb/9/98/Kin-dza-dza.JPG/240px-Kin-dza-dza.JPG",
                "30.03.1987",
                "7.9",
                "12+",
                "Прораб Владимир Николаевич Машков и не подозревал, что обычный путь до универсама за хлебом и макаронами обернется межгалактическими путешествиями. А все эта встреча со странным босоногим человеком с каким-то маленьким устройством — «машинкой перемещения», как тот ее назвал. Короче, нажал на кнопку и оказался вместе со случайным попутчиком, студентом в кроличьей шапке, в пустыне, и не в каких-нибудь Каракумах, а на планете-пустыне Плюк в тентуре, галактика Кин-дза-дза в спирали."
            ),
            Movie(
                1,
                "Kill the Dragon",
                "https://upload.wikimedia.org/wikipedia/ru/thumb/0/08/Ubit_drakona_movie_poster.jpg/274px-Ubit_drakona_movie_poster.jpg",
                "26.06.1988",
                "7.9",
                "12+",
                "Странствующий рыцарь Ланцелот вступает в город, который находится под властью жестокого дракона. Сбежав от тюремщиков, он оказывается в доме архивариуса. Дочь архивариуса Эльзу должны по традиции принести в жертву тирану. Чтобы спасти девушку, а вместе с ней и всех жителей города, Ланцелот осмеливается вызвать дракона на дуэль."
            ),
            Movie(
                2,
                "Gisaengchung",
                "https://upload.wikimedia.org/wikipedia/ru/thumb/7/77/%D0%A4%D0%B8%D0%BB%D1%8C%D0%BC_%D0%9F%D0%B0%D1%80%D0%B0%D0%B7%D0%B8%D1%82%D1%8B_%28Gisaengchung%29.png/210px-%D0%A4%D0%B8%D0%BB%D1%8C%D0%BC_%D0%9F%D0%B0%D1%80%D0%B0%D0%B7%D0%B8%D1%82%D1%8B_%28Gisaengchung%29.png",
                "21.05.2019",
                "8.5",
                "18+",
                "Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan."
            ),
            Movie(
                3,
                "Fight Club",
                "https://upload.wikimedia.org/wikipedia/ru/thumb/8/8a/Fight_club.jpg/239px-Fight_club.jpg",
                "15.10.1999",
                "8.8",
                "18+",
                "An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into much more."
            ),
            Movie(
                4,
                "Ostrov Sokrovishch",
                "https://upload.wikimedia.org/wikipedia/ru/f/f5/Ostrov_sokrovish_anim.jpg",
                "24.10.1988",
                "8.2",
                "6+",
                "Cartoon adaptation of Robert Louis Stevenson's most famous adventure novel."
            ),
        )
    }

    fun onPressItemMovieScreen(id: Int?): Movie {
        return let { (movies.filter { it.id == id  }).first() }
    }
}