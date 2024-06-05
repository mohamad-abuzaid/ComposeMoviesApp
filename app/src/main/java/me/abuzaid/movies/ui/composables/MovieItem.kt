package me.abuzaid.movies.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.abuzaid.movies.R

/**
 * Created by "Mohamad Abuzaid" on 03/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    image: String,
    title: String,
    year: String
) {
    Column(
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.width(180.dp),
            painter = painterResource(id = R.drawable.ic_movies),
            contentDescription = "Movies Image"
        )

        Text(
            modifier = Modifier.padding(top = 25.dp, start = 10.dp),
            text = stringResource(id = R.string.shows),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            modifier = Modifier.padding(top = 25.dp, start = 10.dp),
            text = stringResource(id = R.string.shows),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}

@Preview(showSystemUi = false, showBackground = false, locale = "en")
@Composable
fun PreviewMovieItem() {
    MovieItem(
        image = "",
        title = "Secret Wars",
        year = "2022",
    )
}