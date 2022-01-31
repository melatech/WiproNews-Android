package com.melatech.wipronews.domain.repository

import com.melatech.wipronews.data.model.APIResponse
import com.melatech.wipronews.data.model.Article
import com.melatech.wipronews.data.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 *created by Jason Junior Calvert on 26/01/2022.
 */
interface NewsRepository {

    //These 2 functions are for network communications
    suspend fun getNewsHeadlines_repo(country: String, page:Int): Resource<APIResponse>
    suspend fun getSearchedNews_repo(country: String, searchQuery: String, page:Int): Resource<APIResponse>

    //These 3 functions are for local database communications
    suspend fun saveNews_repo(article: Article)
    suspend fun deleteNews_repo(article: Article)
    fun getSavedNews_repo(): Flow<List<Article>>
}