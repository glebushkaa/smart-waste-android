package ua.glebm.smartwaste.data.network.dto.bucket

import com.google.gson.annotations.SerializedName

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/16/2023
 */

data class BucketsListDto(
    @SerializedName("items") val buckets: List<BucketItemDto> = emptyList(),
)

data class BucketItemDto(
    @SerializedName("id") val id: Long? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("categories") val categories: List<CategoryDto> = emptyList(),
//    @SerializedName("limit") val limit: Int? = null,
) {
    data class CategoryDto(
        @SerializedName("id") val id: Long? = null,
        @SerializedName("name") val name: String? = null,
        @SerializedName("slug") val slug: String? = null,
        @SerializedName("emoji") val icon: String? = null,
    )
}
