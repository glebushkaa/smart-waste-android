package ua.glebm.smartwaste.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import ua.glebm.smartwaste.domain.datastore.AuthDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/27/2023
 */

class AuthDataStoreImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : AuthDataStore {

    override val accessToken: Flow<String?>
        get() = dataStore.data.map {
            it[stringPreferencesKey(USER_ACCESS_TOKEN)]
        }

    override suspend fun getAccessToken(): String? {
        val preferencesKey = stringPreferencesKey(USER_ACCESS_TOKEN)
        val preferences = dataStore.data.first()
        return preferences[preferencesKey]
    }

    override suspend fun updateAccessToken(token: String) {
        dataStore.edit {
            it[stringPreferencesKey(USER_ACCESS_TOKEN)] = token
        }
    }

    override suspend fun removeAccessToken() {
        dataStore.edit {
            it.remove(stringPreferencesKey(USER_ACCESS_TOKEN))
        }
    }

    private companion object {
        const val USER_ACCESS_TOKEN = "user_access_token"
    }
}
