package ua.glebm.smartwaste.data.network

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import ua.glebm.smartwaste.data.network.dto.auth.AuthResponse
import ua.glebm.smartwaste.data.network.dto.auth.DeleteAccountResponse
import ua.glebm.smartwaste.data.network.dto.auth.SignInDto
import ua.glebm.smartwaste.data.network.dto.auth.SignOutDto
import ua.glebm.smartwaste.data.network.dto.auth.UserDto

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

    @GET("self")
    suspend fun getUser(
        @Header("Authorization") token: String,
    ): UserDto

    @DELETE("self")
    suspend fun deleteAccount(
        @Header("Authorization") token: String,
    ): DeleteAccountResponse
}
