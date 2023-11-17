package ua.glebm.smartwaste.ui.navigation.destination

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ua.glebm.smartwaste.ui.navigation.route.ProfileScreenRoute
import ua.glebm.smartwaste.ui.screen.profile.ProfileScreen
import ua.glebm.smartwaste.ui.screen.profile.ProfileViewModel

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/15/2023
 */

fun NavGraphBuilder.profileScreenDestination() {
    composable(route = ProfileScreenRoute.route) {
        val viewModel = hiltViewModel<ProfileViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()

        ProfileScreen(
            state = state,
        )
    }
}
