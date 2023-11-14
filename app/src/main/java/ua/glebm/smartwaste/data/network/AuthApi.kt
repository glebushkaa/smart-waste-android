package ua.glebm.smartwaste.data.network

import ua.glebm.smartwaste.data.network.dto.auth.AuthResponse
import ua.glebm.smartwaste.data.network.dto.auth.DeleteAccountResponse
import ua.glebm.smartwaste.data.network.dto.auth.SignInDto
import ua.glebm.smartwaste.data.network.dto.auth.SignOutDto
import ua.glebm.smartwaste.data.network.dto.auth.UserDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/27/2023
 */

interface AuthApi {

    @POST("auth/signin")
    suspend fun login(
        @Body signInDto: SignInDto,
    ): AuthResponse

    @POST("auth/signup")
    suspend fun register(
        @Body signOutDto: SignOutDto,
    ): AuthResponse

    @GET("auth/me")
    suspend fun getUser(
        @Header("Authorization") token: String,
    ): UserDto

    @DELETE("auth/me")
    suspend fun deleteAccount(
        @Header("Authorization") token: String,
    ): DeleteAccountResponse
}
