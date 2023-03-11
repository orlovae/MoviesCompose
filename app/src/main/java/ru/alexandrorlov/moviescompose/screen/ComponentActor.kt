package ru.alexandrorlov.moviescompose.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.alexandrorlov.moviescompose.R
import ru.alexandrorlov.moviescompose.model.ui.Actor

@Composable
fun ComponentActor(
    actor: Actor
) {
    Column{
        AsyncImage(
            model = actor.photo,
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            error = painterResource(id = R.drawable.ic_launcher_error),
            contentDescription = null,
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(20.dp)
                ),
            contentScale = ContentScale.FillWidth

        )
        //TODO разобраться с шириной текста.
        Text(
            text = actor.name,
            fontSize = 12.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                top = 12.dp
            ),
            maxLines = 2
        )
    }
}

@Preview
@Composable
fun ComponentActorPreview() {
    ComponentActor(
        Actor(
            0,
            "Tenoch Huerta Mejía",
            "https://image.tmdb.org/t/p/w185/5qoAmQpcPCjf4Pd6aTZOeINGYzk.jpg"
        )
    )
}