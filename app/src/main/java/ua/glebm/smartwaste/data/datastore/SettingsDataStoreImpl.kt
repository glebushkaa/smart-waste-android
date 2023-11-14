package ua.glebm.smartwaste.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import ua.glebm.smartwaste.domain.datastore.SettingsDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/26/2023
 */

class SettingsDataStoreImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : SettingsDataStore {

    override val darkModeFlow: Flow<Boolean?>
        get() = dataStore.data.map {
            it[booleanPreferencesKey(DARK_MODE_ENABLED)]
        }

    override suspend fun updateDarkMode(enabled: Boolean) {
        dataStore.edit {
            it[booleanPreferencesKey(DARK_MODE_ENABLED)] = enabled
        }
    }

    private companion object {
        const val DARK_MODE_ENABLED = "dark_mode_enabled"
    }
}
