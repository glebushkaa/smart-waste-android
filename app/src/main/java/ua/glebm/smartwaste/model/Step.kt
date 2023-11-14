package ua.glebm.smartwaste.model

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/31/2023
 */

data class Step(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val order: Int,
    val guideId: String
)