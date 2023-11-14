package ua.glebm.smartwaste.data.mapper

import ua.glebm.smartwaste.data.network.dto.guide.GuideDto
import ua.glebm.smartwaste.model.Guide

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/27/2023
 */

fun GuideDto.toGuide(): Guide {
    return Guide(
        id = id ?: "",
        title = title ?: "",
        emoji = emoji ?: "",
        description = description ?: "",
    )
}
