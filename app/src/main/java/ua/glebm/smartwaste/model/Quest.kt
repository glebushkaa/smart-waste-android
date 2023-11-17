package ua.glebm.smartwaste.model

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/16/2023
 */

data class Quest(
    val id: Long,
    val title: String,
    val totalValue: Int,
    val completeValue: Int,
)
