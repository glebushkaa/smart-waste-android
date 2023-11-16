package ua.glebm.smartwaste.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import ua.glebm.smartwaste.ui.theme.SWTheme.typography

val LocalTypography = staticCompositionLocalOf { GuideTypography() }
val LocalPalette = staticCompositionLocalOf { Palette() }
val LocalOffset = staticCompositionLocalOf { Offset() }
val LocalShapes = staticCompositionLocalOf { Shape() }
val LocalElevation = staticCompositionLocalOf { Elevation() }

@Composable
fun SWTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val palette = if (darkTheme) darkPalette else lightPalette
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = palette.background.toArgb()
            window.navigationBarColor = palette.background.toArgb()
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = !darkTheme
                isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }
    CompositionLocalProvider(
        LocalTypography provides typography,
        LocalPalette provides palette,
        content = content,
    )
}

object SWTheme {
    val typography: GuideTypography
        @Composable
        get() = LocalTypography.current

    val palette: Palette
        @Composable
        get() = LocalPalette.current

    val offset: Offset
        @Composable
        get() = LocalOffset.current

    val shape: Shape
        @Composable
        get() = LocalShapes.current

    val elevation: Elevation
        @Composable
        @ReadOnlyComposable
        get() = LocalElevation.current
}
