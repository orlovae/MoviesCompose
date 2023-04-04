package ru.alexandrorlov.moviescompose.screen.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import ru.alexandrorlov.moviescompose.R

@Composable
fun ComponentStateError(
    messageError: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.error) +
                    System.lineSeparator() +
                    messageError,
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            textAlign = TextAlign.Center
        )
    }
}