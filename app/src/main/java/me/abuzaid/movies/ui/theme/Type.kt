package me.abuzaid.movies.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import me.abuzaid.movies.R

val axiforma = FontFamily(
    Font(R.font.axiforma_bold, weight = FontWeight.Bold),
    Font(R.font.axiforma_semibold, weight = FontWeight.SemiBold),
    Font(R.font.axiforma_regular, weight = FontWeight.Normal)
)

// Set of Material typography styles to start with
val Typography = Typography(

    bodyLarge = TextStyle(
        fontFamily = axiforma,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )

)
