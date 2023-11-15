package ua.glebm.smartwaste.ui.screen.profile

import ua.glebm.smartwaste.model.Achievement

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/15/2023
 */

data class ProfileState(
    val username: String = "",
    val picturePath: String? = null,
    val achievements: List<Achievement> = emptyList(),
)
