package ua.glebm.smartwaste.data.network

import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import ua.glebm.smartwaste.data.network.dto.bucket.BucketDumpItemDto
import ua.glebm.smartwaste.data.network.dto.bucket.BucketsListDto

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/16/2023
 */

interface BucketApi {

    @Multipart
    @POST("scan")
    suspend fun scan(
        @Part data: MultipartBody.Part,
        @Header("Authorization") token: String,
    ): BucketsListDto

    @GET("items")
    suspend fun getItems(
        @Header("Authorization") token: String,
    ): BucketsListDto

    @POST("dump")
    suspend fun dumpBucketItems(
        @Header("Authorization") token: String,
        @Body items: List<BucketDumpItemDto>,
    )
}
