package ua.glebm.smartwaste.ui.screen.login

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/27/2023
 */

sealed class LoginNavigationEffect {

    data object NavigateToHomeScreen : LoginNavigationEffect()

    inline fun handle(
        navigateHome: () -> Unit = {},
    ) {
        when (this) {
            is NavigateToHomeScreen -> navigateHome()
        }
    }
}
