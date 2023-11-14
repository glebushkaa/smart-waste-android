package ua.glebm.smartwaste.data.mapper

import ua.glebm.smartwaste.data.network.dto.guide.GuideStepDto
import ua.glebm.smartwaste.model.Step

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/31/2023
 */

fun GuideStepDto.toStep(): Step {
    return Step(
        title = title ?: "",
        description = description ?: "",
        imageUrl = imageUrl ?: "",
        id = id ?: "",
        order = order ?: 0,
        guideId = guideId ?: "",
    )
}