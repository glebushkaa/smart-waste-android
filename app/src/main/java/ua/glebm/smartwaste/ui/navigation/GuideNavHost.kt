package ua.glebm.smartwaste.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ua.glebm.smartwaste.core.android.extensions.navigatePopUpInclusive
import ua.glebm.smartwaste.ui.navigation.destination.cameraScreenDestination
import ua.glebm.smartwaste.ui.navigation.destination.loginScreenDestination
import ua.glebm.smartwaste.ui.navigation.destination.mapScreenDestination
import ua.glebm.smartwaste.ui.navigation.destination.splashScreenDestination
import ua.glebm.smartwaste.ui.navigation.route.LoginScreenRoute
import ua.glebm.smartwaste.ui.navigation.route.MapScreenRoute
import ua.glebm.smartwaste.ui.navigation.route.SplashScreenRoute

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/26/2023
 */

@Composable
fun GuideNavHost(
    modifier: Modifier = Modifier,
    controller: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = controller,
        startDestination = SplashScreenRoute.route,
    ) {
        splashScreenDestination(
            loginNavigate = {
                controller.navigatePopUpInclusive(
                    route = LoginScreenRoute.route,
                    popUpRoute = SplashScreenRoute.route,
                )
            },
            mapNavigate = {
                controller.navigatePopUpInclusive(
                    route = MapScreenRoute.route,
                    popUpRoute = SplashScreenRoute.route,
                )
            },
        )
        loginScreenDestination {
            controller.navigatePopUpInclusive(
                route = MapScreenRoute.route,
                popUpRoute = LoginScreenRoute.route,
            )
        }

        mapScreenDestination()
        cameraScreenDestination()
    }
}
