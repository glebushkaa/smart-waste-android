package ua.glebm.smartwaste.data.database.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/16/2023
 */

data class BucketWithCategories(
    @Embedded val bucket: BucketItemEntity,
    @Relation(
        parentColumn = "bucketItemId",
        entityColumn = "categoryId",
        associateBy = Junction(BucketCategoryCrossRef::class),
    )
    val categories: List<BucketCategoryEntity>,
)
