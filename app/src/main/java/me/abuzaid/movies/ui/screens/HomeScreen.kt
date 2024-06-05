package me.abuzaid.movies.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import me.abuzaid.movies.R
import me.abuzaid.movies.ui.composables.MovieItem
import me.abuzaid.movies.ui.composables.inputfields.InputTextField
import me.abuzaid.movies.ui.composables.pages.ScreenPage
import me.abuzaid.movies.utils.Dummy

/**
 * Created by "Mohamad Abuzaid" on 01/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
@Composable
fun HomeScreen(
    navController: NavController
) {
    val searchQuery = remember { mutableStateOf("") }

    ScreenPage(
        pullRefreshEnabled = true,
        onRefresh = { }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 25.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Text(
                text = stringResource(id = R.string.search_title),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            InputTextField(
                placeholder = stringResource(R.string.search_title)
            ) {
                searchQuery.value = it
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.categories_title),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier.wrapContentSize(),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Image(
                        modifier = Modifier.width(180.dp),
                        painter = painterResource(id = R.drawable.ic_movies),
                        contentDescription = "Movies Image"
                    )

                    Text(
                        modifier = Modifier.padding(top = 25.dp, end = 10.dp),
                        text = stringResource(id = R.string.movies),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Box(
                    modifier = Modifier.wrapContentSize(),
                    contentAlignment = Alignment.TopStart
                ) {
                    Image(
                        modifier = Modifier.width(175.dp),
                        painter = painterResource(id = R.drawable.ic_shows),
                        contentDescription = "Movies Image"
                    )

                    Text(
                        modifier = Modifier.padding(top = 25.dp, start = 10.dp),
                        text = stringResource(id = R.string.shows),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.popular_title),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            val items = Dummy.movies

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxSize()
            ) {
                items(items.size) { index ->
                    MovieItem(movieItem = items[index])
                }
            }

        }
    }
}

@Preview(showSystemUi = false, showBackground = true, locale = "en")
@Composable
fun PreviewHomeScreen() {
    HomeScreen(
        navController = rememberNavController()
    )
}