package ua.glebm.smartwaste.data.network

import ua.glebm.smartwaste.data.network.dto.guide.GuideDto
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/30/2023
 */

interface FavoritesApi {
    @GET("search/favorite/guides")
    suspend fun searchFavoriteGuides(
        @Header("Authorization") token: String,
        @Query("query") query: String = "",
    ): List<GuideDto>

    @PUT("favorite/guides/{id}")
    suspend fun addFavorite(
        @Header("Authorization") token: String,
        @Path(value = "id") id: String,
    )

    @DELETE("favorite/guides/{id}")
    suspend fun deleteFavorite(
        @Header("Authorization") token: String,
        @Path(value = "id") id: String,
    )
}
