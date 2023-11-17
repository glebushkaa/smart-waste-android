package ua.glebm.smartwaste.ui.screen.profile

import ua.glebm.smartwaste.model.Quest

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/15/2023
 */

data class ProfileState(
    val username: String = "",
    val email: String = "",
    val level: Int = 0,
    val doneBuckets: Int = 0,
    val days: Int = 0,
    val quests: List<Quest> = emptyList(),
    val completedLevelProgress: Int = 0,
    val requiredLevelProgress: Int = 0,
)
