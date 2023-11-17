package ua.glebm.smartwaste.model

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/27/2023
 */

data class User(
    val id: String,
    val username: String,
    val email: String,
    val score: Int = 0,
    val days: Int = 0,
    val buckets: Int = 0,
)

fun emptyUser(): User = User(
    id = "",
    username = "",
    email = "",
)
