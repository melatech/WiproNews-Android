package com.melatech.wipronews.presentation.di

import com.melatech.wipronews.BuildConfig
import com.melatech.wipronews.data.api.NewsAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 *created by Jason Junior Calvert on 28/01/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsAPIService(retrofit: Retrofit): NewsAPIService{
        return retrofit.create(NewsAPIService::class.java)

    }
}