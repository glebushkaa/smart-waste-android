package ua.glebm.smartwaste.data.network.dto.recycle

import com.google.gson.annotations.SerializedName
import ua.glebm.smartwaste.data.network.dto.bucket.BucketItemDto

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/14/2023
 */

data class RecyclePointDto(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("lat") val latitude: Double? = null,
    @SerializedName("lng") val longitude: Double? = null,
    @SerializedName("address") val address: String? = null,
    @SerializedName("categories") val categories: List<BucketItemDto.CategoryDto> = emptyList(),
)
