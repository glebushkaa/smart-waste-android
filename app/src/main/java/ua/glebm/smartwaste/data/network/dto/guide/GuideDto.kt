package ua.glebm.smartwaste.data.network.dto.guide

import com.google.gson.annotations.SerializedName

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/27/2023
 */

data class GuideDto(
    @SerializedName("id") val id: String? = null,
    @SerializedName("emoji") val emoji: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("description") val description: String? = null,
)
