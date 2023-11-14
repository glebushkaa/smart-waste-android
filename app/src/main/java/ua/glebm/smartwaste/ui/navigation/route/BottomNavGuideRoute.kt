package ua.glebm.smartwaste.ui.navigation.route

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/26/2023
 *
 * This interface is used to create routes for bottom navigation items
 *
 * @property titleResId - navigation route title res id
 * @property iconResId - navigation route icon res id
 *
 */

interface BottomNavGuideRoute : GuideRoute {
    val titleResId: Int
    val iconResId: Int
}
