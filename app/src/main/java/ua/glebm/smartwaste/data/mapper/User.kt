package ua.glebm.smartwaste.data.mapper

import ua.glebm.smartwaste.data.network.dto.auth.UserDto
import ua.glebm.smartwaste.model.User

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/14/2023
 */

fun UserDto.toUser(): User {
    return User(
        id = id ?: "",
        username = username ?: "",
        email = email ?: "",
    )
}
