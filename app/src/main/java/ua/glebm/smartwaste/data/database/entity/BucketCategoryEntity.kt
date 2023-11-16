package ua.glebm.smartwaste.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/16/2023
 */

@Entity("bucket_category")
data class BucketCategoryEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("categoryId")
    val id: Long,
    val name: String,
    val slug: String
)
