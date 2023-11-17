package ua.glebm.smartwaste.ui.navigation.destination

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ua.glebm.smartwaste.ui.navigation.route.MapScreenRoute
import ua.glebm.smartwaste.ui.screen.map.MapScreen
import ua.glebm.smartwaste.ui.screen.map.MapViewModel

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/14/2023
 */

fun NavGraphBuilder.mapScreenDestination() {
    composable(route = MapScreenRoute.route) {
        MapDestinationContent()
    }

    composable(
        route = MapScreenRoute.routeWithEnabledArg,
        arguments = MapScreenRoute.enabledArguments,
        content = {
            MapDestinationContent()
        },
    )
}

@Composable
private fun MapDestinationContent() {
    val viewModel = hiltViewModel<MapViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    MapScreen(
        state = state,
        sendEvent = viewModel.state::handleEvent,
    )
}
