package ua.glebm.smartwaste.di

import ua.glebm.smartwaste.data.repository.AuthRepositoryImpl
import ua.glebm.smartwaste.data.repository.FavoritesRepositoryImpl
import ua.glebm.smartwaste.data.repository.GuideRepositoryImpl
import ua.glebm.smartwaste.domain.repository.AuthRepository
import ua.glebm.smartwaste.domain.repository.FavoritesRepository
import ua.glebm.smartwaste.domain.repository.GuideRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/27/2023
 */

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindGuideRepository(guideRepositoryImpl: GuideRepositoryImpl): GuideRepository

    @Binds
    @Singleton
    fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    fun bindFavoritesRepository(favoritesRepositoryImpl: FavoritesRepositoryImpl): FavoritesRepository
}
