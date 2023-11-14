package ua.glebm.smartwaste.data.network.dto.guide

import com.google.gson.annotations.SerializedName

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/28/2023
 */

class GuideDetailsDto(
    @SerializedName("id") val id: String? = null,
    @SerializedName("emoji") val emoji: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("image") val imageUrl: String? = null,
    @SerializedName("authorId") val authorId: String? = null,
    @SerializedName("author") val author: AuthorDto? = null,
    @SerializedName("isFavorite") val favorite: Boolean = false,
) {
    data class AuthorDto(
        @SerializedName("username") val name: String? = null,
    )
}
