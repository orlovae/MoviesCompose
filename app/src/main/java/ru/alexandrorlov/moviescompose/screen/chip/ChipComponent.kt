package ru.alexandrorlov.moviescompose.screen.chip

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipComponent(
    genre: String,
    onSelected: (Boolean) -> Unit
) {
    var selected by rememberSaveable() {
        mutableStateOf(false)
    }
    FilterChip(
        selected = selected,
        onClick = {
            selected = !selected
            onSelected(selected)
        },
        enabled = true,
        modifier = Modifier
            .padding(
                end = 9.dp
            ),
        border = BorderStroke(
            1.dp,
            Color.Black
        ),
        colors = ChipDefaults.filterChipColors(
            disabledBackgroundColor = Color.Green,
            disabledContentColor = Color.Black,
            selectedBackgroundColor = Color.Black,
            selectedContentColor = Color.White,
            backgroundColor = Color.White

        ),

    ) {
        Text(
            text = genre,
            fontSize = 10.sp
        )
    }
}

@Preview
@Composable
fun ChipComponentPreview() {
    ChipComponent(
        genre = "боевик",
        onSelected = {}
    )
}