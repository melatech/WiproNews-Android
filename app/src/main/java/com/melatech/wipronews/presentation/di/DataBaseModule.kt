package com.melatech.wipronews.presentation.di

import android.app.Application
import androidx.room.Room
import com.melatech.wipronews.data.db.ArticleDAO
import com.melatech.wipronews.data.db.ArticleDatabase
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
class DataBaseModule {
    @Provides
    @Singleton
    fun provideNewsDataBase(app: Application): ArticleDatabase{
        return Room.databaseBuilder(app, ArticleDatabase::class.java, "news_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(articleDatabase: ArticleDatabase): ArticleDAO {
        return articleDatabase.getArticleDAO_db()

    }
}