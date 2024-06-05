package me.abuzaid.movies.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import me.abuzaid.movies.models.MovieDisplay
import me.abuzaid.movies.utils.Dummy

/**
 * Created by "Mohamad Abuzaid" on 03/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
@Composable
fun MovieItem(
    movieItem: MovieDisplay
) {
    Column(
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier.width(180.dp),
            model = movieItem.posterPath,
            loading = { CircularProgressIndicator() },
            contentDescription = "Movie Image",
        )

        Text(
            modifier = Modifier.padding(top = 25.dp, start = 10.dp),
            text = movieItem.title,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            modifier = Modifier.padding(top = 25.dp, start = 10.dp),
            text = movieItem.releaseDate,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}

@Preview(showSystemUi = false, showBackground = false, locale = "en")
@Composable
fun PreviewMovieItem() {
    MovieItem(
        movieItem = Dummy.movie1
    )
}