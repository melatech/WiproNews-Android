package com.melatech.wipronews.presentation.di

import com.melatech.wipronews.presentation.adapter.NewsAdapter
import dagger.Module
import dagger.Provides

import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *created by Jason Junior Calvert on 29/01/2022.
 */

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Provides
    @Singleton
    fun provideNewsAdapter(): NewsAdapter{
        return NewsAdapter()
    }
}