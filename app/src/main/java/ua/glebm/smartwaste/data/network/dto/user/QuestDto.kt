package ua.glebm.smartwaste.data.network.dto.user

import com.google.gson.annotations.SerializedName

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/17/2023
 */

data class QuestsListDto(
    @SerializedName("quests") val quests: List<QuestDto> = emptyList(),
)

data class QuestDto(
    @SerializedName("id") val id: Long? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("total") val total: Int = 0,
    @SerializedName("completed") val completed: Int = 0,
)
