package ua.glebm.smartwaste.data.mapper

import ua.glebm.smartwaste.data.network.dto.recycle.RecyclePointDto
import ua.glebm.smartwaste.model.RecyclePoint

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/14/2023
 */

fun RecyclePointDto.toRecyclePoint(): RecyclePoint {
    return RecyclePoint(
        id = id ?: "",
        name = name ?: "",
        latitude = latitude ?: 0.0,
        longitude = longitude ?: 0.0,
        address = address ?: "",
        categories = categories.map { it.toCategory() },
    )
}
