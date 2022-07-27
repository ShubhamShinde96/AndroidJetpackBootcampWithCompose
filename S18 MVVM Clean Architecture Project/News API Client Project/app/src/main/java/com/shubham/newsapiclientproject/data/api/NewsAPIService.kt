package com.shubham.newsapiclientproject.data.api

import com.shubham.newsapiclientproject.BuildConfig
import com.shubham.newsapiclientproject.data.model.API_Response
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {

    // Since we are using coroutines with retrofit we need to use suspend keyword to this function.
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country")
        country: String,
        @Query("page")
        page: Int,
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ): Response<API_Response>


    @GET("v2/top-headlines")
    suspend fun getSearchedTopHeadlines(
        @Query("country")
        country: String,
        @Query("q")
        searchQuery: String,
        @Query("page")
        page: Int,
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ): Response<API_Response>

}