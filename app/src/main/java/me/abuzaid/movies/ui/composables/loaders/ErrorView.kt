package me.abuzaid.movies.ui.composables.loaders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.abuzaid.movies.R
import me.abuzaid.movies.ui.composables.buttons.MainRoundedButton

/**
 * Created by "Mohamad Abuzaid" on 08/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
@Composable
fun ErrorView(
    errorText: String,
    onRetryClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .clip(MaterialTheme.shapes.medium),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    modifier = Modifier.size(60.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_error_alert_dialog),
                    contentDescription = "Error Icon",
                    tint = Color.Red
                )

                Text(
                    text = errorText,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(25.dp))
                MainRoundedButton(
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(horizontal = 16.dp, vertical = 5.dp)
                        .wrapContentHeight(),
                    text = stringResource(id = R.string.try_again)
                ) {
                    onRetryClick()
                }
            }
        }
    }
}


@Preview(showSystemUi = false, showBackground = true, locale = "ar")
@Composable
fun PreviewErrorView() {
    ErrorView(errorText = stringResource(R.string.error_occurred)) {}
}