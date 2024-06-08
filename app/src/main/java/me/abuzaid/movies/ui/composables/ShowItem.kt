package me.abuzaid.movies.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.util.DebugLogger
import me.abuzaid.movies.R
import me.abuzaid.movies.models.ShowDisplay
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
fun ShowItem(
    showItem: ShowDisplay,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(10.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
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
                .size(120.dp)
                .clip(shape = RoundedCornerShape(12.dp)),
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://image.tmdb.org/t/p/original${showItem.posterPath}")
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

        Text(
            modifier = Modifier.padding(top = 15.dp),
            text = showItem.name,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = showItem.firstAirDate.split("-")[0],
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Preview(showSystemUi = false, showBackground = false, locale = "en")
@Composable
fun PreviewShowItem() {
    ShowItem(
        showItem = Dummy.show
    ) {}
}