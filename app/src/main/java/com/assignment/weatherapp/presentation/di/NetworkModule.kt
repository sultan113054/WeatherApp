package com.assignment.weatherapp.presentation.di

import com.assignment.weatherapp.BuildConfig
import com.assignment.weatherapp.WeatherAPIService
import com.assignment.weatherapp.data.util.ResultCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .baseUrl(BuildConfig.BASE_URL).client(
                OkHttpClient.Builder().apply {
                    this.addInterceptor(HttpLoggingInterceptor().apply {
                        this.level = HttpLoggingInterceptor.Level.BODY
                    })
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                }.build()
            )

            .build()
    }

    @Singleton
    @Provides
    fun provideWeatherAPIService(retrofit: Retrofit): WeatherAPIService {
        return retrofit.create(WeatherAPIService::class.java)
    }

}













