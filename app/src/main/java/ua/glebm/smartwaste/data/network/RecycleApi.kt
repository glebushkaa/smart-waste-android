package ua.glebm.smartwaste.data.network

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import ua.glebm.smartwaste.data.network.dto.recycle.RecyclePointDto

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/14/2023
 */

interface RecycleApi {

    @GET("points")
    suspend fun getRecyclePoints(
        @Header("Authorization") accessToken: String,
        @Query("categories") categories: List<String> = emptyList(),
    ): List<RecyclePointDto>
}
