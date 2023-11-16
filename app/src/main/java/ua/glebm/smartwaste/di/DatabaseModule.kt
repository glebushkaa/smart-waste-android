package ua.glebm.smartwaste.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ua.glebm.smartwaste.data.database.SWDatabase
import javax.inject.Singleton

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/16/2023
 */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideSWDatabase(
        @ApplicationContext context: Context,
    ): SWDatabase {
        return Room.databaseBuilder(
            context,
            SWDatabase::class.java,
            SWDatabase.NAME,
        ).build()
    }

    @Provides
    @Singleton
    fun provideBucketDao(
        swDatabase: SWDatabase,
    ) = swDatabase.bucketDao()
}
