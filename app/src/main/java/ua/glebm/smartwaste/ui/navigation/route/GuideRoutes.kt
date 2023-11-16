package ua.glebm.smartwaste.ui.navigation.route

import androidx.navigation.NavType
import androidx.navigation.navArgument
import ua.glebm.smartwaste.R

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/26/2023
 *
 * Screens
 *
 */

object SplashScreenRoute : GuideRoute {
    override val route: String = "splash"
}

object LoginScreenRoute : GuideRoute {
    override val route: String = "login"
}

object MapScreenRoute : BottomNavGuideRoute {
    override val route: String = "map"
    override val titleResId: Int = R.string.map
    override val iconResId: Int = R.drawable.ic_map
    const val categoryEnabledArg = "categoryEnabledArg"

    val routeWithEnabledArg = "$route/{$categoryEnabledArg}"
    val enabledArguments = listOf(
        navArgument(categoryEnabledArg) { type = NavType.BoolType },
    )

    fun makeRouteMapEnabledRoute(enabled: Boolean): String {
        return "$route/$enabled"
    }
}

object CameraScreenRoute : GuideRoute {
    override val route: String = "camera"
}

object BucketScreenRoute : BottomNavGuideRoute {
    override val route: String = "bucket"
    override val titleResId: Int = R.string.bucket
    override val iconResId: Int = R.drawable.ic_bucket
}

object ProfileScreenRoute : BottomNavGuideRoute {
    override val route: String = "profile"
    override val titleResId: Int = R.string.profile
    override val iconResId: Int = R.drawable.ic_profile
}
