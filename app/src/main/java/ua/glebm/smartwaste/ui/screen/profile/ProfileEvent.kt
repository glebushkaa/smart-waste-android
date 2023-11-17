package ua.glebm.smartwaste.ui.screen.profile

import ua.glebm.smartwaste.model.Quest
import ua.glebm.smartwaste.model.User

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/15/2023
 */

sealed class ProfileEvent {

    data class UserInfoLoaded(
        val user: User,
        val quests: List<Quest>,
    ) : ProfileEvent()
}
