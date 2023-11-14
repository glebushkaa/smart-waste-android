package ua.glebm.smartwaste.model

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/28/2023
 */

data class GuideDetails(
    val id: String,
    val title: String,
    val emoji: String,
    val description: String,
    val imageUrl: String,
    val authorId: String,
    val authorName: String,
    val favorite: Boolean = false,
)

fun emptyGuideDetails() = GuideDetails(
    id = "",
    title = "",
    emoji = "",
    description = "",
    imageUrl = "",
    authorId = "",
    authorName = "",
    favorite = false,
)
