package ua.glebm.smartwaste.domain.datastore

import kotlinx.coroutines.flow.Flow

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/26/2023
 */

interface SettingsDataStore {

    val darkModeFlow: Flow<Boolean?>

    suspend fun updateDarkMode(enabled: Boolean)
}
