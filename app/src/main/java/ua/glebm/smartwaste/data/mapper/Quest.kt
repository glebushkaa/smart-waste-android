package ua.glebm.smartwaste.data.mapper

import ua.glebm.smartwaste.data.network.dto.user.QuestDto
import ua.glebm.smartwaste.model.Quest

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/17/2023
 */

fun QuestDto.toQuest(): Quest {
    return Quest(
        id = id ?: 0,
        title = name ?: "",
        totalValue = total,
        completeValue = completed,
    )
}
