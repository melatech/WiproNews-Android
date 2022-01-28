package com.melatech.wipronews.presentation.di

import com.melatech.wipronews.data.api.NewsAPIService
import com.melatech.wipronews.data.repository.dataSource.NewsRemoteDataSource
import com.melatech.wipronews.data.repository.dataSourceImpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *created by Jason Junior Calvert on 28/01/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Provides
    @Singleton
    fun provideNewsRemoteDataSource(
        newsAPIService: NewsAPIService
    ): NewsRemoteDataSource{
        return NewsRemoteDataSourceImpl(newsAPIService)

    }
}