package ua.glebm.smartwaste.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import ua.glebm.smartwaste.data.database.entity.BucketCategoryCrossRef
import ua.glebm.smartwaste.data.database.entity.BucketCategoryEntity
import ua.glebm.smartwaste.data.database.entity.BucketItemEntity
import ua.glebm.smartwaste.data.database.entity.BucketWithCategories

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/16/2023
 */

@Dao
interface BucketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(bucketItemEntity: BucketItemEntity)

    @Query(
        """
            UPDATE bucket
            SET count = CASE
            WHEN (count + 1) > 10 THEN 10
            ELSE count + 1
            END
            WHERE name = :name
        """,
    )
    suspend fun increaseItemCount(name: String)

    @Query(
        """
            UPDATE bucket
            SET count = CASE
            WHEN (count - 1) < 0 THEN 0
            ELSE count - 1
            END
            WHERE name = :name
        """,
    )
    suspend fun decreaseItemCount(name: String)

    @Query("SELECT * FROM bucket WHERE count > 0 ORDER BY name ASC")
    fun getBucketFlow(): Flow<List<BucketItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBucket(bucket: BucketItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: BucketCategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategoryList(listCategory: List<BucketCategoryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBucketCategoryCrossRefList(crossRef: List<BucketCategoryCrossRef>)

    @Transaction
    @Query("SELECT * FROM bucket WHERE count > 0 ORDER BY name ASC")
    fun getBucketWithCategoriesFlow(): Flow<List<BucketWithCategories>>

    @Transaction
    @Query("SELECT * FROM bucket WHERE count > 0 ORDER BY name ASC")
    fun getBucketWithCategories(): List<BucketWithCategories>

    @Query("DELETE FROM bucket")
    suspend fun deleteAllItems()

    @Query("DELETE FROM bucket_category")
    suspend fun deleteAllCategories()

    @Query("DELETE FROM bucketcategorycrossref")
    suspend fun deleteAllCrossRef()
}
