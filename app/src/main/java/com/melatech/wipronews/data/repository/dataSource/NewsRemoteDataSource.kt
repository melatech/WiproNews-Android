package com.melatech.wipronews.data.repository.dataSource

import com.melatech.wipronews.data.model.APIResponse
import retrofit2.Response

/**
 *created by Jason Junior Calvert on 27/01/2022.
 */
interface NewsRemoteDataSource {
    //This function will communicate with the api
    //and return a Retrofit Response object
    suspend fun getTopHeadlines_rds(country: String, page:Int): Response<APIResponse>
}