package com.melatech.wipronews.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.melatech.wipronews.domain.usecase.GetNewsHeadlinesUseCase
import com.melatech.wipronews.domain.usecase.GetSearchedNewsUseCase

/**
 *created by Jason Junior Calvert on 28/01/2022.
 */
class NewsViewModelFactory(
    private val app: Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(
            app,
            getNewsHeadlinesUseCase,
            getSearchedNewsUseCase
        ) as T
    }
}