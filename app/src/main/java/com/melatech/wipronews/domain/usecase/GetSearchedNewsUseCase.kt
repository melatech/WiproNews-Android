package com.melatech.wipronews.domain.usecase

import com.melatech.wipronews.data.model.APIResponse
import com.melatech.wipronews.data.util.Resource
import com.melatech.wipronews.domain.repository.NewsRepository

/**
 *created by Jason Junior Calvert on 26/01/2022.
 */
class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(searchQuery: String): Resource<APIResponse>{
        return newsRepository.getSearchedNews_repo(searchQuery)

    }
}