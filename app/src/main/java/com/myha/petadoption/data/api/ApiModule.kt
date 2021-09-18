package com.myha.petadoption.data.api

import android.content.Context
import com.myha.petadoption.data.repository.BaseRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Aria on 9/17/2021.
 */

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private const val BASE_URL = "https://restcountries.eu/rest/v2/"
    private const val CHAT_URL = "https://restcountries.eu/rest/v2/"

    @Provides
    fun provideConnectivity(@ApplicationContext context: Context): Connectivity {
        return Connectivity(context)
    }

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        connectivity: Connectivity,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(8000, TimeUnit.SECONDS)
            .writeTimeout(8000, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(connectivity)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideMoshi() = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideBaseApi(retrofit: Retrofit): BaseService = retrofit.create(BaseService::class.java)

    @Singleton
    @Provides
    fun provideChatApi(retrofit: Retrofit): ChatService = retrofit.create(ChatService::class.java)

    @Singleton
    @Provides
    fun provideBaseRepository(baseService: BaseService) = BaseRepository(baseService)
}