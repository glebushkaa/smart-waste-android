package ua.glebm.smartwaste.ui.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ua.glebm.smartwaste.ui.navigation.route.MapScreenRoute
import ua.glebm.smartwaste.ui.screen.map.MapScreen

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/14/2023
 */

fun NavGraphBuilder.mapScreenDestination() {
    composable(route = MapScreenRoute.route) {
        /* val viewModel = hiltViewModel<LoginViewModel>()
         val state by viewModel.state.collectAsStateWithLifecycle()
         val navigationEffect by viewModel.navigationEffect
             .receiveAsFlow()
             .collectAsStateWithLifecycle(null)*/

        MapScreen(
            /*state = state,
            sendEvent = viewModel.state::handleEvent,*/
        )

        /*LaunchedEffect(key1 = navigationEffect) {
            navigationEffect?.handle(navigateHome = navigateHome)
        }*/
    }
}
