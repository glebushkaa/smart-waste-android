package ua.glebm.smartwaste.core.android.extensions

import androidx.navigation.NavController

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/26/2023
 */

fun NavController.navigatePopUpInclusive(
    route: String,
    popUpRoute: String,
) {
    navigate(route) {
        popUpTo(popUpRoute) {
            inclusive = true
            saveState = true
        }
    }
}

fun NavController.navigateSingleTopTo(
    route: String,
) {
    navigate(
        route = route,
    ) {
        launchSingleTop = true
    }
}
