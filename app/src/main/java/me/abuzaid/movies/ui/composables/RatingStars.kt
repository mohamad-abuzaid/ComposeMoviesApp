package me.abuzaid.movies.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Created by "Mohamad Abuzaid" on 03/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
@Composable
fun RatingStars(
    rate: Double
) {
    Row {
        for (i in 1..5) {
            val icon = when {
                i <= rate -> Icons.Filled.Star
                i - 0.5 == rate -> Icons.AutoMirrored.Filled.StarHalf
                else -> Icons.Outlined.Star
            }
            Icon(
                modifier = Modifier.padding(2.dp),
                imageVector = icon,
                contentDescription = "Star $i",
                tint = if (i <= rate || i - 0.5 == rate) Color.Yellow else Color.Gray
            )
        }
    }
}

@Preview(showSystemUi = false, showBackground = false, locale = "en")
@Composable
fun PreviewRatingStars() {
    RatingStars(rate = 3.5)
}