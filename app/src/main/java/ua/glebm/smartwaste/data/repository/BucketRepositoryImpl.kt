package ua.glebm.smartwaste.data.repository

import android.content.Context
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import ua.glebm.smartwaste.data.database.BucketDao
import ua.glebm.smartwaste.data.database.entity.BucketCategoryCrossRef
import ua.glebm.smartwaste.data.database.entity.BucketWithCategories
import ua.glebm.smartwaste.data.mapper.toBucketEntity
import ua.glebm.smartwaste.data.mapper.toBucketItem
import ua.glebm.smartwaste.data.mapper.toCategoryEntity
import ua.glebm.smartwaste.data.network.BucketApi
import ua.glebm.smartwaste.domain.datastore.AuthDataStore
import ua.glebm.smartwaste.domain.exception.AuthException
import ua.glebm.smartwaste.domain.repository.BucketRepository
import ua.glebm.smartwaste.model.BucketItem
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/16/2023
 */

class BucketRepositoryImpl @Inject constructor(
    private val bucketDao: BucketDao,
    private val bucketApi: BucketApi,
    private val authDataStore: AuthDataStore,
    @ApplicationContext private val context: Context,
) : BucketRepository {

    override suspend fun getAllBucketItems(): List<BucketItem> {
        val token = getAccessToken()
        return bucketApi.getItems(token).buckets.map {
            it.toBucketItem()
        }
    }

    override suspend fun addItem(bucketItem: BucketItem) {
        val categories = bucketItem.categories.map { it.toCategoryEntity() }
        val bucket = bucketItem.toBucketEntity()
        val crossRefs = categories.map {
            BucketCategoryCrossRef(
                bucketItemId = bucketItem.id,
                categoryId = it.id,
            )
        }
        bucketDao.run {
            insertCategoryList(categories)
            insertBucket(bucket)
            insertBucketCategoryCrossRefList(crossRefs)
        }
    }

    override suspend fun increaseItemCount(name: String) {
        bucketDao.increaseItemCount(name)
    }

    override suspend fun decreaseItemCount(name: String) {
        bucketDao.decreaseItemCount(name)
    }

    override suspend fun scanItem(uri: Uri): BucketItem = withContext(Dispatchers.IO) {
        val contentResolver = context.contentResolver
        val inputStream = contentResolver.openInputStream(uri)
        val file = File.createTempFile("upload", "tmp", context.cacheDir)
        inputStream?.use { input ->
            FileOutputStream(file).use { output -> input.copyTo(output) }
        }
        val requestFile = file.asRequestBody()
        val data = MultipartBody.Part.createFormData(
            name = "Photo",
            filename = file.name,
            body = requestFile,
        )
        val token = getAccessToken()
        val bucketDto = bucketApi.scan(
            data = data,
            token = token,
        )
        return@withContext bucketDto.buckets.first().toBucketItem()
    }

    override fun getBucketFlow(): Flow<List<BucketItem>> {
        return bucketDao.getBucketWithCategories().map {
            it.map(BucketWithCategories::toBucketItem)
        }
    }

    private suspend fun getAccessToken(): String {
        return authDataStore.getAccessToken() ?: run {
            val exception = AuthException(
                code = NO_TOKEN_EXCEPTION,
                message = "No token",
            )
            throw exception
        }
    }

    private companion object {
        const val NO_TOKEN_EXCEPTION = -199
    }
}
