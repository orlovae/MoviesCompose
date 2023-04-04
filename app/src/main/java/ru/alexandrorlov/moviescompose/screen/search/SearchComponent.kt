package ru.alexandrorlov.moviescompose.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.alexandrorlov.moviescompose.R
import ru.alexandrorlov.moviescompose.screen.ViewModelMovieList

@Composable
fun SearchComponent(
    viewModel: ViewModelMovieList
) {
    val state = viewModel.stateSearch.collectAsState()
    TextField(
        value = state.value,
        onValueChange = { textSearch ->
            viewModel.updateMovieListWhitSearch(textSearch)
        },
        modifier = Modifier
            .height(73.dp)
            .fillMaxWidth()
            .background(Color.White),
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 18.sp
        ),
        placeholder = { Text(text = stringResource(id = R.string.search)) },
        trailingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(10.dp)
                    .size(30.dp)
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}