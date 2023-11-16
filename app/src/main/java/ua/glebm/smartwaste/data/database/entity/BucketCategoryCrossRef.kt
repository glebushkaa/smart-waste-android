package ua.glebm.smartwaste.data.database.entity

import androidx.room.Entity

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/16/2023
 */

@Entity(primaryKeys = ["bucketItemId", "categoryId"])
data class BucketCategoryCrossRef(
    val bucketItemId: Long,
    val categoryId: Long,
)
