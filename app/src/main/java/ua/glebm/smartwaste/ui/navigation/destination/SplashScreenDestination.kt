package ua.glebm.smartwaste.ui.navigation.destination

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.coroutines.flow.receiveAsFlow
import ua.glebm.smartwaste.core.common.FIVE_HUNDRED_MILLIS
import ua.glebm.smartwaste.ui.navigation.route.SplashScreenRoute
import ua.glebm.smartwaste.ui.screen.splash.SplashScreen
import ua.glebm.smartwaste.ui.screen.splash.SplashViewModel

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/26/2023
 */

private val exitAnimSpec = tween<Float>(FIVE_HUNDRED_MILLIS.toInt())

fun NavGraphBuilder.splashScreenDestination(
    loginNavigate: () -> Unit = {},
    mapNavigate: () -> Unit = {},
) {
    composable(
        route = SplashScreenRoute.route,
        exitTransition = { fadeOut(animationSpec = exitAnimSpec) },
    ) {
        val viewModel = hiltViewModel<SplashViewModel>()
        val navEffect by viewModel.navigationEffect
            .receiveAsFlow()
            .collectAsStateWithLifecycle(initialValue = null)

        SplashScreen()

        LaunchedEffect(key1 = navEffect) {
            navEffect?.handle(
                navigateLogin = loginNavigate,
                navigateMap = mapNavigate,
            )
        }
    }
}
