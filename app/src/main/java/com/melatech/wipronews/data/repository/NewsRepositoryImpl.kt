package com.melatech.wipronews.data.repository

import com.melatech.wipronews.data.model.APIResponse
import com.melatech.wipronews.data.model.Article
import com.melatech.wipronews.data.repository.dataSource.NewsLocalDataSource
import com.melatech.wipronews.data.repository.dataSource.NewsRemoteDataSource
import com.melatech.wipronews.data.util.Resource
import com.melatech.wipronews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

/**
 *created by Jason Junior Calvert on 27/01/2022.
 */
//This class is the implementation for the domain repository interface
class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
): NewsRepository {
    override suspend fun getNewsHeadlines_repo(country: String, page:Int): Resource<APIResponse> {
        return responseToResource(newsRemoteDataSource.getTopHeadlines_rds(country, page))
    }

    override suspend fun getSearchedNews_repo(
        country: String,
        searchQuery: String,
        page: Int
    ): Resource<APIResponse> {
        return responseToResource(newsRemoteDataSource.getSearchedNews_rds(country, searchQuery, page))
    }

    override suspend fun saveNews_repo(article: Article) {
        newsLocalDataSource.saveArticleToDB_lds(article)
    }

    override suspend fun deleteNews_repo(article: Article) {
        newsLocalDataSource.deleteArticlesFromDB_lds(article)
    }

    override fun getSavedNews_repo(): Flow<List<Article>> {
        return newsLocalDataSource.getSavedArticles_lds()
    }

    private fun responseToResource(response: Response<APIResponse>): Resource<APIResponse>{
        if (response.isSuccessful){
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

}