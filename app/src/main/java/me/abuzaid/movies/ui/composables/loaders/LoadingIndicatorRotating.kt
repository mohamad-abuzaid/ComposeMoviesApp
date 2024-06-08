package me.abuzaid.movies.ui.composables.loaders

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import me.abuzaid.movies.R

/**
 * Created by "Mohamad Abuzaid" on 08/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
@Composable
fun LoadingIndicatorRotating(showLoading: Boolean = true) {
    var rotationAngle by remember { mutableFloatStateOf(0f) }

    val image = ImageVector.vectorResource(id = R.drawable.ic_loading_indicator)

    LaunchedEffect(Unit) {
        while (true) {
            rotationAngle += 45f
            delay(100L)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RotatingImage(rotationAngle, image, Modifier.size(50.dp))

            if (showLoading) {
                Text(
                    text = stringResource(id = R.string.loading),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun RotatingImage(rotationAngle: Float, image: ImageVector, modifier: Modifier = Modifier) {
    val animatedRotationAngle by animateFloatAsState(targetValue = rotationAngle, label = "")

    Box(modifier = modifier.rotate(animatedRotationAngle)) {
        Image(imageVector = image, contentDescription = null)
    }
}


@Preview(showSystemUi = false, showBackground = true, locale = "ar")
@Composable
fun PreviewLoadingIndicatorRotating() {
    LoadingIndicatorRotating()
}