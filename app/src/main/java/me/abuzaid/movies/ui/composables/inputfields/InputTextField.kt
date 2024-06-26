package me.abuzaid.movies.ui.composables.inputfields

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.abuzaid.movies.R
import me.abuzaid.movies.ui.theme.Corner

/**
 * Created by "Mohamad Abuzaid" on 01/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    placeholder: String = "",
    isEnabled: Boolean = true,
    onSearch: (() -> Unit)? = null,
    onValueChanged: (String) -> Unit
) {
    var value by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .then(modifier)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(3.dp),
            horizontalAlignment = Alignment.Start
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp),
                value = value,
                enabled = isEnabled,
                textStyle = MaterialTheme.typography.labelSmall,
                shape = RoundedCornerShape(Corner),
                placeholder = {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                },
                trailingIcon = onSearch?.let {
                    {
                        IconButton(onClick = { it() }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Trailing Icon",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    focusedTextColor = MaterialTheme.colorScheme.primary,
                    unfocusedTextColor = MaterialTheme.colorScheme.primary,
                    focusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
                    unfocusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
                    focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.tertiary
                ),
                onValueChange = {
                    value = it
                    onValueChanged(value)
                }
            )
        }
    }
}

@Preview(showSystemUi = false, showBackground = true, locale = "ar")
@Composable
fun PreviewInputTextField() {
    InputTextField(
        placeholder = stringResource(id = R.string.search_title),
        onValueChanged = {}
    )
}