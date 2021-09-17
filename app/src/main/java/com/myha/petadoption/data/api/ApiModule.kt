package com.myha.petadoption.data.api

import com.myha.petadoption.data.repository.BaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Aria on 9/17/2021.
 */

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private const val BASE_URL = "https://restcountries.eu/rest/v2/"
    private const val CHAT_URL = "https://restcountries.eu/rest/v2/"

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor).build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
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