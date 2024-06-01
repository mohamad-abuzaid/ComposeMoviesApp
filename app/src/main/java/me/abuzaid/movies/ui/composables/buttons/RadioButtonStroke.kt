package me.abuzaid.movies.ui.composables.buttons

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import me.abuzaid.movies.ui.theme.Spacing
import me.abuzaid.movies.utils.localization.LocalizationHelper

/**
 * Created by "Mohamad Abuzaid" on 01/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
@Composable
fun RadioButtonStroke(
    text: String,
    iconResId: Int,
    borderWidth: Dp = 0.dp,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    RoundedBorder(cornerRadius = 24.dp, showBorder = text == selectedOption) {
        RoundedRadioButtonWithSelectionStroke(
            text,
            iconResId,
            borderWidth,
            selectedOption,
            onOptionSelected
        )
    }
}

@Composable
private fun RoundedRadioButtonWithSelectionStroke(
    text: String,
    iconResId: Int,
    borderWidth: Dp,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onOptionSelected(text)
            }
            .clip(RoundedCornerShape(50.dp))
            .border(
                width = borderWidth,
                color = MaterialTheme.colorScheme.tertiary,
                shape = RoundedCornerShape(50.dp)
            )
            .background(Color.White)
            .padding(start = 6.dp)
    ) {
        Image(
            painter = painterResource(iconResId),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))
        Text(
            modifier = Modifier.weight(3f),
            text = text,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.titleMedium,
        )
        RadioButton(
            selected = (text == selectedOption),
            onClick = {
                onOptionSelected(text)
            },
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.background,
                unselectedColor = MaterialTheme.colorScheme.background,
            ),
        )
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun RoundedBorder(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 50.dp,
    borderWidth: Dp = 2.dp,
    borderColor: Color = MaterialTheme.colorScheme.secondary,
    showBorder: Boolean = false,
    content: @Composable () -> Unit
) {

    val paint = rememberBorderPaint(borderColor, borderWidth)

    Box(modifier = modifier) {
        Box(modifier = Modifier.padding(borderWidth)) {
            content()
        }
        Canvas(modifier = Modifier.matchParentSize()) {
            drawIntoCanvas { canvas ->
                if (showBorder) {
                    drawRoundedBorder(canvas, size, cornerRadius.toPx(), paint)
                }
            }
        }
    }
}

@Composable
private fun rememberBorderPaint(color: Color, width: Dp): Paint {
    return remember(color, width) {
        Paint().apply {
            this.color = color
            this.style = PaintingStyle.Stroke
            this.strokeWidth = 4.0f
        }
    }
}

private fun drawRoundedBorder(canvas: Canvas, size: Size, cornerRadius: Float, paint: Paint) {
    val halfBorderWidth = paint.strokeWidth / 2f
    val path = Path().apply {
        addRoundRect(
            roundRect = RoundRect(
                left = halfBorderWidth,
                top = halfBorderWidth,
                right = size.width - halfBorderWidth,
                bottom = size.height - halfBorderWidth,
                cornerRadius = CornerRadius(cornerRadius),
            ),

            )
    }
    canvas.drawPath(path, paint)
}

@Preview(showSystemUi = false, showBackground = true, locale = "ar")
@Composable
fun PreviewRadioButtonStroke() {
    val (selectedOption, onOptionSelected) =
        remember { mutableStateOf(LocalizationHelper.langOptions[0]) }

    Column(verticalArrangement = Arrangement.spacedBy(Spacing)) {
        LocalizationHelper.langOptions.forEach { text ->
            RadioButtonStroke(
                text,
                LocalizationHelper.icon(text),
                1.dp,
                selectedOption,
                onOptionSelected
            )
        }
    }
}