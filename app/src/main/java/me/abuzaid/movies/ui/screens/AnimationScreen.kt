package me.abuzaid.movies.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import me.abuzaid.movies.R
import me.abuzaid.movies.ui.composables.buttons.MainRoundedButton
import me.abuzaid.movies.ui.composables.pages.ScreenPage

/**
 * Created by "Mohamad Abuzaid" on 15/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
@Composable
fun AnimationScreen(
    navController: NavController
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
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start
        ) {
            RotatingImage()
            HorizontalDivider(thickness = 2.dp, color = Color.White)

            AnimatedVisibilityExample()
            HorizontalDivider(thickness = 2.dp, color = Color.White)

            StateTransitionExample()
            HorizontalDivider(thickness = 2.dp, color = Color.White)

            PulsingEffect()
            HorizontalDivider(thickness = 2.dp, color = Color.White)

            KeyframePositionAnimation()
            HorizontalDivider(thickness = 2.dp, color = Color.White)

            AnimatedCircleColor()
            HorizontalDivider(thickness = 2.dp, color = Color.White)

            AnimatedCircleRadius()
            HorizontalDivider(thickness = 2.dp, color = Color.White)
        }
    }
}

/**********************************/

@Composable
fun RotatingImage() {
    var rotationDegrees by remember { mutableFloatStateOf(0f) }
    val animatedRotation by animateFloatAsState(
        targetValue = rotationDegrees,
        label = "RotatingImage"
    )

    Image(
        painter = painterResource(id = R.drawable.ic_arabic_lang),
        contentDescription = "Rotatable Image",
        modifier = Modifier
            .size(200.dp)
            .graphicsLayer {
                rotationZ = animatedRotation
            }
            .clickable { rotationDegrees += 90 }
    )
}

/**********************************/

@Composable
fun AnimatedVisibilityExample() {
    var isVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MainRoundedButton(
            modifier = Modifier.width(200.dp),
            text = "Toggle Visibility"
        ) {
            isVisible = !isVisible
        }

        Spacer(modifier = Modifier.height(16.dp))
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn() + expandVertically(),  // Enter transitions
            exit = fadeOut() + slideOutHorizontally()  // Exit transitions
        ) {
            Text(
                "Hello, I'm visible!",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
        }
    }
}

/**********************************/

enum class ButtonState { Normal, Pressed }

@Composable
fun StateTransitionExample() {

    // Manage the state
    var buttonState by remember { mutableStateOf(ButtonState.Normal) }
    val transition = updateTransition(targetState = buttonState, label = "ButtonTransition")

    // Define animations for properties
    val backgroundColor by transition.animateColor(label = "BackgroundColorTransition") { state ->
        when (state) {
            ButtonState.Normal -> Color.Gray
            ButtonState.Pressed -> Color.Blue
        }
    }

    val width by transition.animateDp(label = "WidthTransition") { state ->
        when (state) {
            ButtonState.Normal -> 200.dp
            ButtonState.Pressed -> 300.dp
        }
    }

    Button(
        onClick = {
            buttonState =
                if (buttonState == ButtonState.Normal) ButtonState.Pressed else ButtonState.Normal
        },
        modifier = Modifier
            .width(width)
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor)
    ) {
        Text("Click Me", color = Color.White)
    }
}

/**********************************/

@Composable
fun PulsingEffect() {
    val infiniteTransition = rememberInfiniteTransition(label = "PulsingEffect")
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "PulsingEffect2"
    )

    Box(
        modifier = Modifier
            .size(100.dp)
            .scale(scale)
            .background(Color.Green)
    )
}

/**********************************/

@Composable
fun KeyframePositionAnimation() {
    var targetValue by remember { mutableFloatStateOf(0f) }

    val xOffset by animateFloatAsState(
        targetValue = targetValue,
        animationSpec = keyframes {
            durationMillis = 5000
            0f at 0 using LinearEasing // starting position
            300f at 2500 using LinearEasing // mid position at 2 second
            600f at 5000 // end position at 5 seconds
        },
        label = "KeyframePositionAnimation"
    )

    LaunchedEffect(Unit) {
        targetValue = 600f
    }

    Box(
        modifier = Modifier
            .offset { IntOffset(xOffset.toInt(), 0) }
            .size(50.dp)
            .background(Color.Blue)
    )
}

/**********************************/

@Composable
fun AnimatedCircleColor() {
    val isActivated = remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (isActivated.value) Color.Green else Color.Red,
        animationSpec = tween(durationMillis = 500),
        label = "AnimatedCircleColor"
    )

    Column(
        modifier = Modifier.wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top)
    ) {
        Canvas(modifier = Modifier.size(200.dp)) {
            drawCircle(color = color, radius = 300f)
        }

        MainRoundedButton(
            modifier = Modifier.width(200.dp),
            text = "Animate"
        ) {
            isActivated.value = !isActivated.value
        }
    }
}

/**********************************/

@Composable
fun AnimatedCircleRadius() {
    val radius = remember { Animatable(initialValue = 10f) }

    LaunchedEffect(key1 = true) {
        radius.animateTo(
            targetValue = 300f,
            animationSpec = tween(durationMillis = 1000)
        )
    }

    Canvas(modifier = Modifier.size(200.dp)) {
        drawCircle(color = Color.Blue, radius = radius.value)
    }
}

@Preview(showSystemUi = false, showBackground = true, locale = "en")
@Composable
fun PreviewAnimationScreen() {
    AnimationScreen(
        navController = rememberNavController()
    )
}