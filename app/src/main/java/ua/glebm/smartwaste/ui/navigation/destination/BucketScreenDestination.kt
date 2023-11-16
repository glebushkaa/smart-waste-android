package ua.glebm.smartwaste.ui.navigation.destination

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.coroutines.flow.receiveAsFlow
import ua.glebm.smartwaste.ui.navigation.route.BucketScreenRoute
import ua.glebm.smartwaste.ui.screen.bucket.BucketNavigationEffect
import ua.glebm.smartwaste.ui.screen.bucket.BucketScreen
import ua.glebm.smartwaste.ui.screen.bucket.BucketViewModel

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/16/2023
 */

fun NavGraphBuilder.bucketScreenDestination(
    navigateMap: () -> Unit,
) {
    composable(route = BucketScreenRoute.route) {
        val viewModel = hiltViewModel<BucketViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        val sideEffect by viewModel.sideEffect
            .receiveAsFlow()
            .collectAsStateWithLifecycle(initialValue = null)
        val navigationEffect by viewModel.navigationEffect
            .receiveAsFlow()
            .collectAsStateWithLifecycle(initialValue = null)

        BucketScreen(
            state = state,
            sideEffect = sideEffect,
            sendEvent = viewModel.state::handleEvent,
        )

        LaunchedEffect(key1 = navigationEffect) {
            val navEffect = navigationEffect ?: return@LaunchedEffect
            when (navEffect) {
                is BucketNavigationEffect.NavigateMap -> {
                    navigateMap()
                }
            }
        }
    }
}
