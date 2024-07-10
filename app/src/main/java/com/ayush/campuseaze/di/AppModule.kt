package com.ayush.campuseaze.di

import android.content.Context
import com.ayush.campuseaze.data.repository.AuthRepository
import com.ayush.campuseaze.data.repository.DataStoreRepository
import com.ayush.campuseaze.data.repository.HomeRepository
import com.ayush.campuseaze.data.repository.ProfileRepository
import com.ayush.campuseaze.utils.Constants.BASE_URL
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun providesRetrofit(loggingInterceptor: HttpLoggingInterceptor) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun providesAuthRepository(
        auth: FirebaseAuth,
        retrofit: Retrofit
    ) = AuthRepository(auth = auth, retrofit = retrofit)

    @Provides
    fun providesHomeRepository(
        auth: FirebaseAuth,
        retrofit: Retrofit
    ) = HomeRepository(auth = auth, retrofit = retrofit)

    @Provides
    fun providesDataStoreRepository(
        @ApplicationContext context: Context
    ): DataStoreRepository = DataStoreRepository(context)

    @Provides
    fun providesProfileRepository(
        retrofit: Retrofit
    ) = ProfileRepository(retrofit = retrofit)
}