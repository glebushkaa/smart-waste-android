package ua.glebm.smartwaste.ui.screen.bucket

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/16/2023
 */

sealed class BucketNavigationEffect {

    data object NavigateMap : BucketNavigationEffect()
}
