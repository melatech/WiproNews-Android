package com.melatech.wipronews.data.repository.dataSourceImpl

import com.melatech.wipronews.data.api.NewsAPIService
import com.melatech.wipronews.data.model.APIResponse
import com.melatech.wipronews.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response

/**
 *created by Jason Junior Calvert on 27/01/2022.
 */
//This class is the implementation of the NewsRemoteDataSource interface
class NewsRemoteDataSourceImpl(
    private val newsAPIService: NewsAPIService,
    private val country: String,
    private val page: Int,

): NewsRemoteDataSource {
    override suspend fun getTopHeadlines_rds(): Response<APIResponse> {
        return newsAPIService.getTopHeadlines_service(country, page)
    }
}