package ua.glebm.smartwaste.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.glebm.smartwaste.data.repository.AuthRepositoryImpl
import ua.glebm.smartwaste.data.repository.BucketRepositoryImpl
import ua.glebm.smartwaste.data.repository.RecycleRepositoryImpl
import ua.glebm.smartwaste.domain.repository.AuthRepository
import ua.glebm.smartwaste.domain.repository.BucketRepository
import ua.glebm.smartwaste.domain.repository.RecycleRepository
import javax.inject.Singleton

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/27/2023
 */

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl,
    ): AuthRepository

    @Binds
    @Singleton
    fun bindRecycleRepository(
        recycleRepositoryImpl: RecycleRepositoryImpl,
    ): RecycleRepository

    @Binds
    @Singleton
    fun bindBucketRepository(
        impl: BucketRepositoryImpl,
    ): BucketRepository
}
