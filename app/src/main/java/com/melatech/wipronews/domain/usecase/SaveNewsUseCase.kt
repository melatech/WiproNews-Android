package com.melatech.wipronews.domain.usecase

import com.melatech.wipronews.data.model.Article
import com.melatech.wipronews.domain.repository.NewsRepository

/**
 *created by Jason Junior Calvert on 26/01/2022.
 */
class SaveNewsUseCase(private val newsRepository: NewsRepository) {
    //This function does not return any data therefore we use a single line function
    suspend fun execute(article: Article) = newsRepository.saveNews_repo(article)
}