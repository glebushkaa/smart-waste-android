package ua.glebm.smartwaste.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ua.glebm.smartwaste.data.database.SWDatabase.Companion.VERSION
import ua.glebm.smartwaste.data.database.entity.BucketCategoryCrossRef
import ua.glebm.smartwaste.data.database.entity.BucketCategoryEntity
import ua.glebm.smartwaste.data.database.entity.BucketItemEntity

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/16/2023
 */

@Database(
    entities = [
        BucketItemEntity::class,
        BucketCategoryEntity::class,
        BucketCategoryCrossRef::class,
    ],
    version = VERSION,
    exportSchema = true,
)
abstract class SWDatabase : RoomDatabase() {

    abstract fun bucketDao(): BucketDao

    companion object {
        const val NAME = "sw_database"
        const val VERSION = 1
    }
}
