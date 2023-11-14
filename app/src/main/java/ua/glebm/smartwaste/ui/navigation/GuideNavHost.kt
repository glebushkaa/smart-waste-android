package ua.glebm.smartwaste.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ua.glebm.smartwaste.core.android.extensions.navigatePopUpInclusive
import ua.glebm.smartwaste.ui.navigation.destination.detailsScreenDestination
import ua.glebm.smartwaste.ui.navigation.destination.favoritesScreenDestination
import ua.glebm.smartwaste.ui.navigation.destination.homeScreenDestination
import ua.glebm.smartwaste.ui.navigation.destination.loginScreenDestination
import ua.glebm.smartwaste.ui.navigation.destination.settingsScreenDestination
import ua.glebm.smartwaste.ui.navigation.destination.splashScreenDestination
import ua.glebm.smartwaste.ui.navigation.destination.stepsScreenDestination
import ua.glebm.smartwaste.ui.navigation.route.DetailsScreenRoute
import ua.glebm.smartwaste.ui.navigation.route.HomeScreenRoute
import ua.glebm.smartwaste.ui.navigation.route.LoginScreenRoute
import ua.glebm.smartwaste.ui.navigation.route.SettingsScreenRoute
import ua.glebm.smartwaste.ui.navigation.route.SplashScreenRoute
import ua.glebm.smartwaste.ui.navigation.route.StepsScreenRoute

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
            homeNavigate = {
                controller.navigatePopUpInclusive(
                    route = HomeScreenRoute.route,
                    popUpRoute = SplashScreenRoute.route,
                )
            },
        )
        homeScreenDestination { id ->
            val route = DetailsScreenRoute.makeDetailsScreenRoute(id = id)
            controller.navigate(route = route)
        }
        favoritesScreenDestination { id ->
            val route = DetailsScreenRoute.makeDetailsScreenRoute(id = id)
            controller.navigate(route = route)
        }
        detailsScreenDestination(
            navigateBack = {
                controller.popBackStack()
            },
            navigateSteps = { guideId ->
                val route = StepsScreenRoute.makeStepsScreenRoute(id = guideId)
                controller.navigate(route = route)
            }
        )
        settingsScreenDestination {
            controller.navigatePopUpInclusive(
                route = LoginScreenRoute.route,
                popUpRoute = SettingsScreenRoute.route,
            )
        }

        loginScreenDestination {
            controller.navigatePopUpInclusive(
                route = HomeScreenRoute.route,
                popUpRoute = LoginScreenRoute.route,
            )
        }

        stepsScreenDestination()
    }
}
