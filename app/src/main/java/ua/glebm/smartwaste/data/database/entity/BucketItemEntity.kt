package ua.glebm.smartwaste.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/16/2023
 */

@Entity("bucket")
data class BucketItemEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("bucketItemId")
    val id: Long,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("count") val count: Int,
)
