package ua.glebm.smartwaste.ui.navigation.route

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
    override val iconResId: Int = R.drawable.ic_home
}

object CameraScreenRoute : BottomNavGuideRoute {
    override val route: String = "camera"
    override val titleResId: Int = R.string.camera
    override val iconResId: Int = R.drawable.ic_settings
}

object ProfileScreenRoute : BottomNavGuideRoute {
    override val route: String = "profile"
    override val titleResId: Int = R.string.profile
    override val iconResId: Int = R.drawable.ic_profile
}
