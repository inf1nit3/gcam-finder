package com.example.gcamfinder.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = ApertureGold,
    onPrimary = TextOnAccent,
    secondary = ZeissCyan,
    onSecondary = TextOnAccent,
    tertiary = ZeissCyan,
    background = AmoledBlack,
    onBackground = TextPrimary,
    surface = SurfaceCard,
    onSurface = TextPrimary,
    surfaceVariant = BorderSlate,
    onSurfaceVariant = TextSecondary,
    error = ErrorRed,
    onError = Color.White
)

@Composable
fun GCamFinderTheme(
    darkTheme: Boolean = true, // AMOLED dark mode first!
    content: @Composable () -> Unit,
) {
    // We lock in our bespoke AMOLED-dark design system to deliver a gorgeous premium camera feel
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
