package com.melatech.wipronews.presentation.di

import com.melatech.wipronews.data.repository.NewsRepositoryImpl
import com.melatech.wipronews.data.repository.dataSource.NewsLocalDataSource
import com.melatech.wipronews.data.repository.dataSource.NewsRemoteDataSource
import com.melatech.wipronews.domain.repository.NewsRepository
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
class RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource,
        newsLocalDataSource: NewsLocalDataSource

    ): NewsRepository{
        return NewsRepositoryImpl(
            newsRemoteDataSource,
            newsLocalDataSource
        )

    }
}