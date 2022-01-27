package com.melatech.wipronews.domain.usecase

import com.melatech.wipronews.data.model.Article
import com.melatech.wipronews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

/**
 *created by Jason Junior Calvert on 26/01/2022.
 */
class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {
    //This function returns a Flow so no need for suspend modifier
     fun execute(): Flow<List<Article>> {
         return newsRepository.getSavedNews_repo()

    }
}