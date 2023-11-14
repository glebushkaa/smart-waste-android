package ua.glebm.smartwaste.data.repository

import ua.glebm.smartwaste.data.mapper.toUser
import ua.glebm.smartwaste.data.network.AuthApi
import ua.glebm.smartwaste.data.network.dto.auth.AuthResponse
import ua.glebm.smartwaste.data.network.dto.auth.SignInDto
import ua.glebm.smartwaste.data.network.dto.auth.SignOutDto
import ua.glebm.smartwaste.data.network.dto.error.LoginErrorResponse
import ua.glebm.smartwaste.domain.datastore.AuthDataStore
import ua.glebm.smartwaste.domain.exception.AuthException
import ua.glebm.smartwaste.domain.repository.AuthRepository
import ua.glebm.smartwaste.domain.usecase.auth.LoginField
import ua.glebm.smartwaste.domain.usecase.auth.SignInUseCase
import ua.glebm.smartwaste.domain.usecase.auth.SignUpUseCase
import ua.glebm.smartwaste.model.User
import com.google.gson.Gson
import retrofit2.HttpException
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/27/2023
 */

class AuthRepositoryImpl @Inject constructor(
    private val authDataStore: AuthDataStore,
    private val authApi: AuthApi,
) : AuthRepository {

    override suspend fun login(email: String, password: String): String {
        val response: AuthResponse
        try {
            val signInDto = SignInDto(email = email, password = password)
            response = authApi.login(signInDto)
        } catch (httpException: HttpException) {
            val errorResponse = httpException.response() ?: run {
                throw httpException
            }
            val json = errorResponse.errorBody()?.string()
            val signInResponse = Gson().fromJson(json, LoginErrorResponse::class.java)
            throw getSignInException(signInResponse.code, signInResponse.message)
        }
        return response.accessToken ?: run {
            val exception = AuthException(
                code = LOGIN_EXCEPTION,
                message = response.message ?: "Unknown error",
            )
            throw exception
        }
    }

    override suspend fun register(username: String, email: String, password: String): String {
        val response: AuthResponse
        try {
            val signOutDto = SignOutDto(
                username = username,
                email = email,
                password = password,
            )
            response = authApi.register(signOutDto)
        } catch (httpException: HttpException) {
            val responseError = httpException.response() ?: run {
                throw httpException
            }
            val json = responseError.errorBody()?.string()
            val signOut = Gson().fromJson(json, LoginErrorResponse::class.java)
            throw getSignUpException(
                code = signOut.code,
                message = signOut.message,
            )
        }
        return response.accessToken ?: run {
            val exception = AuthException(
                code = REGISTER_EXCEPTION,
                message = response.message ?: "Unknown error",
            )
            throw exception
        }
    }

    override suspend fun getUser(): User {
        val accessToken = getAccessToken()
        val response = authApi.getUser(accessToken)
        if (response.message?.isNotEmpty() == true) {
            val exception = AuthException(
                code = GET_USER_EXCEPTION,
                message = response.message,
            )
            throw exception
        }
        return response.toUser()
    }

    override suspend fun deleteAccount() {
        val accessToken = getAccessToken()
        val response = authApi.deleteAccount(accessToken)
        if (response.code?.isNotEmpty() == true && response.message?.isNotEmpty() == true) {
            val exception = AuthException(
                code = DELETE_ACCOUNT_EXCEPTION,
                message = response.message,
            )
            throw exception
        }
    }

    private suspend fun getAccessToken(): String {
        return authDataStore.getAccessToken() ?: run {
            val exception = AuthException(
                code = NO_TOKEN_EXCEPTION,
                message = "No token",
            )
            throw exception
        }
    }

    private fun getSignInException(code: String, message: String?) = when (code) {
        PASSWORD_IS_NOT_VALID -> SignInUseCase.SignInException(
            field = LoginField.PASSWORD,
            message = message ?: "Invalid password",
        )

        USER_NOT_FOUND -> SignInUseCase.SignInException(
            field = LoginField.EMAIL,
            message = message ?: "User not found",
        )

        else -> SignInUseCase.SignInException(message = message ?: "Unknown error")
    }

    private fun getSignUpException(code: String, message: String?) = when (code) {
        EMAIL_NOT_UNIQUE -> SignUpUseCase.SignUpException(
            field = LoginField.EMAIL,
            message = message ?: "Email is already taken",
        )

        USERNAME_NOT_UNIQUE -> SignUpUseCase.SignUpException(
            field = LoginField.USERNAME,
            message = message ?: "Username is already taken",
        )

        else -> SignUpUseCase.SignUpException(message = message ?: "Unknown error")
    }

    private companion object {
        const val LOGIN_EXCEPTION = 100
        const val REGISTER_EXCEPTION = 200
        const val GET_USER_EXCEPTION = 300
        const val DELETE_ACCOUNT_EXCEPTION = -180
        const val NO_TOKEN_EXCEPTION = -199

        const val PASSWORD_IS_NOT_VALID = "invalid-password"
        const val USER_NOT_FOUND = "user-not-found"
        const val EMAIL_NOT_UNIQUE = "email-not-unique"
        const val USERNAME_NOT_UNIQUE = "username-not-unique"
    }
}
