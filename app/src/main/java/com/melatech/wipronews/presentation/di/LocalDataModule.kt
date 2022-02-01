package com.melatech.wipronews.presentation.di

import com.melatech.wipronews.data.db.ArticleDAO
import com.melatech.wipronews.data.repository.dataSource.NewsLocalDataSource
import com.melatech.wipronews.data.repository.dataSourceImpl.NewsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *created by Jason Junior Calvert on 31/01/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Provides
    @Singleton
    fun provideNewsLocalDataSource(articleDAO: ArticleDAO): NewsLocalDataSource{
        return NewsLocalDataSourceImpl(articleDAO)

    }
}