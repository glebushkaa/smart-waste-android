package ua.glebm.smartwaste.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.glebm.smartwaste.data.network.AuthApi
import ua.glebm.smartwaste.data.network.FavoritesApi
import ua.glebm.smartwaste.data.network.GuidesApi
import ua.glebm.smartwaste.session.api.SessionStatusHandler
import ua.glebm.smartwaste.session.impl.SessionStatusHandlerImpl
import javax.inject.Singleton

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/27/2023
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val GUIDE_BASE_URL = "https://guidebook-api.azurewebsites.net/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(GUIDE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideGuidesApi(retrofit: Retrofit): GuidesApi {
        return retrofit.create(GuidesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFavoritesApi(retrofit: Retrofit): FavoritesApi {
        return retrofit.create(FavoritesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSessionStatusHandler(): SessionStatusHandler {
        return SessionStatusHandlerImpl()
    }
}
