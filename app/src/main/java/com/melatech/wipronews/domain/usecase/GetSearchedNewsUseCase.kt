package com.melatech.wipronews.domain.usecase

import com.melatech.wipronews.data.model.APIResponse
import com.melatech.wipronews.data.util.Resource
import com.melatech.wipronews.domain.repository.NewsRepository

/**
 *created by Jason Junior Calvert on 26/01/2022.
 */
class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(country: String, searchQuery: String, page: Int): Resource<APIResponse>{
        return newsRepository.getSearchedNews_repo(country, searchQuery, page)

    }
}