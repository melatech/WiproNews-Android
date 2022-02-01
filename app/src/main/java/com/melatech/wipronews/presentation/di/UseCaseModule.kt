package com.melatech.wipronews.presentation.di

import com.melatech.wipronews.domain.repository.NewsRepository
import com.melatech.wipronews.domain.usecase.*
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
class UseCaseModule {

    @Provides
    @Singleton
    fun provideNewsHeadLinesUseCase(
        newsRepository: NewsRepository
    ): GetNewsHeadlinesUseCase{
        return GetNewsHeadlinesUseCase(newsRepository)
    }

    @Provides
    @Singleton
    fun provideGetNewsSearchUseCase(
        newsRepository: NewsRepository
    ): GetSearchedNewsUseCase{
        return GetSearchedNewsUseCase(newsRepository)
    }

    @Provides
    @Singleton
    fun provideSaveNewsUseCase(
        newsRepository: NewsRepository
    ): SaveNewsUseCase{
        return SaveNewsUseCase(newsRepository)
    }

    @Provides
    @Singleton
    fun provideGetSavedNewsUseCase(
        newsRepository: NewsRepository
    ): GetSavedNewsUseCase {
        return GetSavedNewsUseCase(newsRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteSavedNewsUseCase(
        newsRepository: NewsRepository
    ): DeleteSavedNewsUseCase {
        return DeleteSavedNewsUseCase(newsRepository)
    }
}