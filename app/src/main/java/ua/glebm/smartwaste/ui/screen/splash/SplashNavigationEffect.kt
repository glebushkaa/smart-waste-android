package ua.glebm.smartwaste.ui.screen.splash

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/28/2023
 */

sealed class SplashNavigationEffect {

    data object NavigateToLogin : SplashNavigationEffect()

    data object NavigateToMap : SplashNavigationEffect()

    inline fun handle(
        navigateLogin: () -> Unit = {},
        navigateMap: () -> Unit = {},
    ) = when (this) {
        is NavigateToLogin -> navigateLogin()
        is NavigateToMap -> navigateMap()
    }
}
