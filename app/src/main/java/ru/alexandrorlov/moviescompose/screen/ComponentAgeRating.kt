package ru.alexandrorlov.moviescompose.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//TODO избавиться от хардкора в padding и radius, т.к. при смене шрифта всё едет
@Composable
fun ComponentAgeRating(ageRating: String) {
    Text(
        text = ageRating,
        fontSize = 14.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(11.dp)
            .drawBehind {
                drawCircle(
                    Color.Black,
                    radius = 40f,
                    style = Stroke(
                        width = 2f
                    )
                )
            }
    )
}

@Preview
@Composable
fun ComponentAgeRatingPreview(
) {
    ComponentAgeRating(
        "18+"
    )
}