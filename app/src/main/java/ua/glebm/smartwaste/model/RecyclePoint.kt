package ua.glebm.smartwaste.model

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/14/2023
 */

data class RecyclePoint(
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val address: String,
)
