package ua.glebm.smartwaste.domain.datastore

import kotlinx.coroutines.flow.Flow

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/27/2023
 */

interface AuthDataStore {

    val accessToken: Flow<String?>

    suspend fun getAccessToken(): String?

    suspend fun updateAccessToken(token: String)

    suspend fun removeAccessToken()
}
