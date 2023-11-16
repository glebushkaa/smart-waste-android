package ua.glebm.smartwaste.data.repository

import ua.glebm.smartwaste.data.mapper.toRecyclePoint
import ua.glebm.smartwaste.data.network.RecycleApi
import ua.glebm.smartwaste.domain.datastore.AuthDataStore
import ua.glebm.smartwaste.domain.exception.AuthException
import ua.glebm.smartwaste.domain.repository.RecycleRepository
import ua.glebm.smartwaste.model.RecyclePoint
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/14/2023
 */

class RecycleRepositoryImpl @Inject constructor(
    private val recycleApi: RecycleApi,
    private val authDataStore: AuthDataStore,
) : RecycleRepository {

    override suspend fun getRecyclePoints(): List<RecyclePoint> {
        val accessToken = getAccessToken()
        return recycleApi.getRecyclePoints(
            accessToken = accessToken,
        ).map { it.toRecyclePoint() }
    }

    override suspend fun getRecyclePointsByCategories(categories: List<String>): List<RecyclePoint> {
        val accessToken = getAccessToken()
        return recycleApi.getRecyclePoints(
            accessToken = accessToken,
            categories = categories,
        ).map { it.toRecyclePoint() }
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

    private companion object {
        const val NO_TOKEN_EXCEPTION = -199
    }
}
