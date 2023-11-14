package ua.glebm.smartwaste.data.repository

import ua.glebm.smartwaste.data.mapper.toGuide
import ua.glebm.smartwaste.data.network.FavoritesApi
import ua.glebm.smartwaste.domain.datastore.AuthDataStore
import ua.glebm.smartwaste.domain.exception.AuthException
import ua.glebm.smartwaste.domain.repository.FavoritesRepository
import ua.glebm.smartwaste.model.Guide
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/30/2023
 */

class FavoritesRepositoryImpl @Inject constructor(
    private val favoritesApi: FavoritesApi,
    private val authDataStore: AuthDataStore,
) : FavoritesRepository {

    override suspend fun addFavorite(id: String) {
        val accessToken = getAccessToken()
        favoritesApi.addFavorite(token = accessToken, id = id)
    }

    override suspend fun removeFavorite(id: String) {
        val accessToken = getAccessToken()
        favoritesApi.deleteFavorite(token = accessToken, id = id)
    }

    override suspend fun searchFavorites(query: String): List<Guide> {
        return favoritesApi.searchFavoriteGuides(
            token = getAccessToken(),
            query = query,
        ).map { it.toGuide() }
    }

    private suspend fun getAccessToken(): String {
        return authDataStore.getAccessToken() ?: run {
            val exception = AuthException(
                code = NO_TOKEN_EXCEPTION,
                message = "No token found",
            )
            throw exception
        }
    }

    private companion object {
        const val NO_TOKEN_EXCEPTION = -199
    }
}
