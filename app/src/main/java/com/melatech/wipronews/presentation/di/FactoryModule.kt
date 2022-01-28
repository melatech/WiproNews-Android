package com.melatech.wipronews.presentation.di

import android.app.Application
import com.melatech.wipronews.domain.usecase.GetNewsHeadlinesUseCase
import com.melatech.wipronews.presentation.viewmodel.NewsViewModel
import com.melatech.wipronews.presentation.viewmodel.NewsViewModelFactory
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
class FactoryModule {

    @Provides
    @Singleton
    fun provideNewsViewModelFactory(
        app: Application,
        getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase
    ): NewsViewModelFactory{
        return NewsViewModelFactory(
            app,
            getNewsHeadlinesUseCase
        )


    }
}