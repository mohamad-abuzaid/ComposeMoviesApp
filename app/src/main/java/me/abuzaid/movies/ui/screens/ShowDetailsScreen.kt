package me.abuzaid.movies.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.util.DebugLogger
import me.abuzaid.movies.R
import me.abuzaid.movies.models.ShowDisplay
import me.abuzaid.movies.ui.composables.ActorItem
import me.abuzaid.movies.ui.composables.RatingStars
import me.abuzaid.movies.ui.composables.buttons.MainRoundedButton
import me.abuzaid.movies.ui.composables.pages.ScreenPage
import me.abuzaid.movies.utils.Dummy
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.compose.getKoin
import org.koin.core.qualifier.named

/**
 * Created by "Mohamad Abuzaid" on 08/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
@Composable
fun ShowDetailsScreen(
    navController: NavController,
    show: ShowDisplay
) {
    ScreenPage(
        onBack = { navController.popBackStack() },
        pullRefreshEnabled = false,
        onRefresh = { }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top
        ) {
            val token = getKoin().get<String>(named("accessToken"))
            val imageLoader = ImageLoader.Builder(LocalContext.current)
                .okHttpClient {
                    OkHttpClient.Builder()
                        .addInterceptor { chain ->
                            val request = chain.request()
                            val newRequest: Request = request.newBuilder()
                                .header("Authorization", "Bearer $token")
                                .method(request.method, request.body)
                                .build()
                            chain.proceed(newRequest)
                        }
                        .build()
                }
                .respectCacheHeaders(false)
                .logger(DebugLogger())
                .build()

            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://image.tmdb.org/t/p/original${show.posterPath}")
                    .networkCachePolicy(CachePolicy.ENABLED)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .memoryCachePolicy(CachePolicy.DISABLED)
                    .build(),
                contentScale = ContentScale.FillBounds,
                imageLoader = imageLoader,
                loading = { CircularProgressIndicator() },
                error = {
                    Image(
                        painter = painterResource(id = R.drawable.image_placeholder),
                        contentDescription = "Placeholder Image"
                    )
                },
                contentDescription = "Movie Image",
            )

            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            modifier = Modifier.weight(4f),
                            text = show.name,
                            style = MaterialTheme.typography.displayLarge,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Text(
                            modifier = Modifier.weight(1f),
                            text = show.firstAirDate.split("-")[0],
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        RatingStars(rate = show.voteAverage)

                        Text(
                            text = stringResource(R.string.votes_number, show.voteCount),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = show.overview,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.secondary,
                    lineHeight = 25.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                val actors = Dummy.actors
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(25.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(actors) { actor ->
                        ActorItem(actor = actor)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                MainRoundedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                    text = stringResource(R.string.watch_now)
                ) {}
                Spacer(modifier = Modifier.height(25.dp))
            }
        }
    }
}

@Preview(showSystemUi = false, showBackground = true, locale = "en")
@Composable
fun PreviewShowDetailsScreen() {
    ShowDetailsScreen(
        navController = rememberNavController(),
        show = Dummy.show
    )
}