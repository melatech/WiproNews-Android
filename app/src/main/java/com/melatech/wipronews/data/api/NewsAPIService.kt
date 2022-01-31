package com.melatech.wipronews.data.api

import com.melatech.wipronews.BuildConfig
import com.melatech.wipronews.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *created by Jason Junior Calvert on 27/01/2022.
 */
interface NewsAPIService {
    //This function uses kotlin coroutines with Retrofit(suspend keyword)
    // to return a Retrofit Response object
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines_service(
        @Query("country")
        country: String,
        @Query("page")
        page: Int,
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ):Response<APIResponse>

    @GET("v2/top-headlines")
    suspend fun getSearchedTopHeadlines_service(
        @Query("country")
        country: String,
        @Query("q")
        q: String,
        @Query("page")
        page: Int,
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ):Response<APIResponse>
}