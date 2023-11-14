package ua.glebm.smartwaste.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import ua.glebm.smartwaste.data.datastore.SettingsDataStoreImpl
import ua.glebm.smartwaste.data.datastore.AuthDataStoreImpl
import ua.glebm.smartwaste.domain.datastore.SettingsDataStore
import ua.glebm.smartwaste.domain.datastore.AuthDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/26/2023
 */

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    private const val SETTINGS_DATA_STORE = "settings_datastore"
    private const val SETTINGS_DATA_STORE_NAME = "settings_datastore_name"

    private const val USER_DATA_STORE = "user_datastore"
    private const val USER_DATA_STORE_NAME = "user_datastore_name"

    @Provides
    @Singleton
    @Named(SETTINGS_DATA_STORE)
    fun provideSettingsPreferences(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile(SETTINGS_DATA_STORE_NAME) },
        )
    }

    @Provides
    @Singleton
    fun provideSettingsDataStore(
        @Named(SETTINGS_DATA_STORE) dataStore: DataStore<Preferences>,
    ): SettingsDataStore {
        return SettingsDataStoreImpl(dataStore)
    }

    @Provides
    @Singleton
    @Named(USER_DATA_STORE)
    fun provideUserPreferences(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile(USER_DATA_STORE_NAME) },
        )
    }

    @Provides
    @Singleton
    fun provideUserDataStore(
        @Named(USER_DATA_STORE) dataStore: DataStore<Preferences>,
    ): AuthDataStore {
        return AuthDataStoreImpl(dataStore)
    }
}
