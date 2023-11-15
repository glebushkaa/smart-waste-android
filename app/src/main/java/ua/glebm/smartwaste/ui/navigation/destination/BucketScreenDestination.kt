package ua.glebm.smartwaste.ui.navigation.destination

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ua.glebm.smartwaste.ui.navigation.route.BucketScreenRoute
import ua.glebm.smartwaste.ui.screen.bucket.BucketScreen
import ua.glebm.smartwaste.ui.screen.bucket.BucketViewModel

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/16/2023
 */

fun NavGraphBuilder.bucketScreenDestination() {
    composable(route = BucketScreenRoute.route) {
        val viewModel = hiltViewModel<BucketViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()

        BucketScreen(
            state = state,
            sendEvent = viewModel.state::handleEvent,
        )
    }
}
