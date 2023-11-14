package ua.glebm.smartwaste.domain.exception

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/31/2023
 */

data class SessionExpiredException(
    override val message: String,
) : Exception(message)