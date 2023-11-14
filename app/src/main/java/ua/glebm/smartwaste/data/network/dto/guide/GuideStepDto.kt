package ua.glebm.smartwaste.data.network.dto.guide

import com.google.gson.annotations.SerializedName

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/31/2023
 */

data class GuideStepDto(
    @SerializedName("id") val id: String? = null,
    @SerializedName("order") val order: Int? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("image") val imageUrl: String? = null,
    @SerializedName("guideId") val guideId: String? = null,
)
