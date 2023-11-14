package ua.glebm.smartwaste.data.network.dto.error

import com.google.gson.annotations.SerializedName

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/30/2023
 */

data class LoginErrorResponse(
    @SerializedName("code") val code: String,
    @SerializedName("message") val message: String,
)