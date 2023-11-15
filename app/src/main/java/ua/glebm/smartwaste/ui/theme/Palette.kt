package ua.glebm.smartwaste.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/26/2023
 */

data class Palette(
    val primary: Color = Green,
    val secondary: Color = Color.Green,
    val tertiary: Color = Color.Red,
    val background: Color = BackgroundWhite,
    val surface: Color = Color.White,
    val error: Color = Color.Red,
    val onPrimary: Color = Color.White,
    val onSecondary: Color = Color.White,
    val onTertiary: Color = Color.White,
    val onBackground: Color = Color.Black,
    val onSurface: Color = Color.Black,
    val onError: Color = Color.Red,
)

val darkPalette = Palette(
    primary = Green,
    secondary = DarkGreen,
    tertiary = DarkRed,
    background = DarkGrey,
    surface = NewSurfaceBlack,
    error = ErrorRed,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color.White,
    onSurface = NewOnSurfaceBlack,
    onError = BackgroundWhite,
)

val lightPalette = Palette(
    primary = Green,
    secondary = Color.Green,
    tertiary = Color.Red,
    background = BackgroundWhite,
    surface = Color.White,
    error = ErrorRed,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color.Black,
    onSurface = NewSurfaceBlack,
    onError = BackgroundWhite,
)
