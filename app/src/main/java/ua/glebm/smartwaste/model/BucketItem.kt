package ua.glebm.smartwaste.model

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/15/2023
 */

data class BucketItem(
    val id: Long,
    val name: String,
    val count: Int,
    val limit: Int = 10,
)
