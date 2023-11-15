package ua.glebm.smartwaste.ui.navigation.destination

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ua.glebm.smartwaste.ui.navigation.route.CameraScreenRoute
import ua.glebm.smartwaste.ui.screen.camera.CameraScreen
import ua.glebm.smartwaste.ui.screen.camera.CameraViewModel

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/14/2023
 */

fun NavGraphBuilder.cameraScreenDestination() {
    composable(route = CameraScreenRoute.route) {
        val viewModel = hiltViewModel<CameraViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()

        CameraScreen(
            state = state,
            sendEvent = viewModel.state::handleEvent,
        )
    }
}
