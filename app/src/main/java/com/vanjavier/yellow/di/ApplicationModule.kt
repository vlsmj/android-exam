package com.vanjavier.yellow.di

import android.app.Application
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.vanjavier.local.YellowDatabase
import com.vanjavier.network.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Prepare the single instance application module components to be used
 * across the application.
 */
@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    /**
     * Provide the Room database instance.
     *
     * @param application The Application instance.
     */
    @Provides
    fun providesYellowDatabase(application: Application): YellowDatabase =
        Room.databaseBuilder(application, YellowDatabase::class.java, "yellow_db")
            .fallbackToDestructiveMigration()
            .build()

    /**
     * Provide the API service with the corresponding endpoints.
     */
    @Provides
    fun providesApiService(): ApiService {

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
            chain.proceed(
                chain.request().newBuilder()
                    .addHeader(
                        "Authorization", "Bearer tTCg5emLIqSIh5BzxKl6ag"
                    )
                    .build()
            )
        }).addInterceptor(httpLoggingInterceptor).build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://app.fakejson.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(ApiService::class.java)
    }
}