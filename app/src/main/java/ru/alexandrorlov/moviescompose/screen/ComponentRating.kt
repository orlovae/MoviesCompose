package ru.alexandrorlov.moviescompose.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.alexandrorlov.moviescompose.R
import ru.alexandrorlov.moviescompose.config.AppConfig
import ru.alexandrorlov.moviescompose.config.TypeStar
import ru.alexandrorlov.moviescompose.config.UIConfig.STAR_BORDER
import ru.alexandrorlov.moviescompose.config.UIConfig.STAR_FULL

@Composable
fun ComponentRating(starsMovie: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        for (i in 0..AppConfig.STARS_RATING_COUNT) {
            if (i < starsMovie) {
                ComponentStar(type = TypeStar.STAR_FULL)
            } else {
                ComponentStar(type = TypeStar.STAR_EMPTY)
            }
        }
    }
}

@Composable
fun ComponentStar(type: TypeStar) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(14.dp)
            .width(14.dp)
    ) {
        when (type) {
            TypeStar.STAR_FULL -> Icon(
                painter = painterResource(id = R.drawable.icon_star_full),
                tint = Color.Unspecified,
                contentDescription = STAR_FULL
            )
            TypeStar.STAR_EMPTY -> Icon(
                painter = painterResource(id = R.drawable.icon_star_empty),
                tint = Color.Unspecified,
                contentDescription = STAR_BORDER
            )
        }
    }
}

@Preview
@Composable
fun ComponentRatingPreview() {
    ComponentRating(3)
}