package ua.glebm.smartwaste.data.network

import ua.glebm.smartwaste.data.network.dto.guide.GuideDetailsDto
import ua.glebm.smartwaste.data.network.dto.guide.GuideDto
import ua.glebm.smartwaste.data.network.dto.guide.GuideStepDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/27/2023
 */

interface GuidesApi {

    @GET("search/guides")
    suspend fun searchGuides(
        @Header("Authorization") token: String,
        @Query("query") query: String = "",
    ): List<GuideDto>

    @GET("guide/{guideId}")
    suspend fun getGuideDetails(
        @Header("Authorization") token: String,
        @Path(value = "guideId") id: String,
    ): GuideDetailsDto

    @GET("guide/{guideId}/steps")
    suspend fun getGuideSteps(
        @Header("Authorization") token: String,
        @Path(value = "guideId") id: String,
    ): List<GuideStepDto>
}
