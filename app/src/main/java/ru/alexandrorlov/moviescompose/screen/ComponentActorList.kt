package ru.alexandrorlov.moviescompose.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.alexandrorlov.moviescompose.model.ui.Actor

@Composable
fun ComponentActorList(
    actorList: List<Actor>
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp,
                top = 16.dp,
                end = 16.dp
            )
    ) {
        LazyRow {
            items(actorList) { actor ->
                ComponentActor(actor = actor)
                Spacer(
                    modifier = Modifier
                        .width(10.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun ComponentActorListPreview() {
    ComponentActorList(
        listOf(
            Actor(
                0,
                "Letitia Wright",
                "https://image.tmdb.org/t/p/w185/4jdsjY5jKwoNpCMd5nnJFsDmieY.jpg"
            ),
            Actor(
                1,
                "Lupita Nyong'o",
                "https://image.tmdb.org/t/p/w185/mJMpsADPpt0bmXEzs3ywrUiCkpD.jpg"
            ),
            Actor(
                2,
                "Tenoch Huerta Mejía",
                "https://image.tmdb.org/t/p/w185/5qoAmQpcPCjf4Pd6aTZOeINGYzk.jpg"
            ),
            Actor(
                3,
                "Danai Gurira",
                "https://image.tmdb.org/t/p/w185/z7H7QeQvr24vskGlANQhG43vozQ.jpg"
            ),
            Actor(
                4,
                "Winston Duke",
                "https://image.tmdb.org/t/p/w185/MhBiZbryibwuoEtPL9Ns8pYHC1.jpg"
            ),
            Actor(
                5,
                "Angela Bassett",
                "https://image.tmdb.org/t/p/w185/7Oz53NKdglRzAzI2MKjM3eQXwn.jpg"
            ),
            Actor(
                6,
                "Florence Kasumba",
                "https://image.tmdb.org/t/p/w185/vivJLQhtwca5hupqoRRgL8BRs6o.jpg"
            )
        )
    )
}