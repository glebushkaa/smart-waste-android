package ua.glebm.smartwaste.data.network.dto.auth

import com.google.gson.annotations.SerializedName

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/27/2023
 */

data class SignOutDto(
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
)
