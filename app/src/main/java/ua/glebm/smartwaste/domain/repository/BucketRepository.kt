package ua.glebm.smartwaste.domain.repository

import android.net.Uri
import kotlinx.coroutines.flow.Flow
import ua.glebm.smartwaste.model.BucketItem

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/16/2023
 */

interface BucketRepository {

    suspend fun addItem(bucketItem: BucketItem)

    suspend fun increaseItemCount(name: String)
    suspend fun decreaseItemCount(name: String)

    suspend fun scanItem(uri: Uri): BucketItem

    suspend fun getAllBucketItems(): List<BucketItem>

    fun getBucketFlow(): Flow<List<BucketItem>>

    suspend fun dumpBucket()

    suspend fun clearBucket()

    suspend fun getCurrentBucketCategorySlugs(): List<String>
}
