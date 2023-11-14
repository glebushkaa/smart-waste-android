package ua.glebm.smartwaste.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.glebm.smartwaste.data.network.AuthApi
import ua.glebm.smartwaste.data.network.RecycleApi
import ua.glebm.smartwaste.session.api.SessionStatusHandler
import ua.glebm.smartwaste.session.impl.SessionStatusHandlerImpl
import javax.inject.Singleton

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 10/27/2023
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val SMART_WASTE_BASE_URL = "https://smartwaste-api.azurewebsites.net/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(SMART_WASTE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRecycleApi(retrofit: Retrofit): RecycleApi {
        return retrofit.create(RecycleApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSessionStatusHandler(): SessionStatusHandler {
        return SessionStatusHandlerImpl()
    }
}
