package com.shubham.newsapiclientproject.data.repository.dataSource

import com.shubham.newsapiclientproject.data.model.API_Response
import retrofit2.Response

interface NewsRemoteDataSource {

    // here we will define abstract function for the functions of NewsAPIService which will communicate with the api.
    suspend fun getTopHeadline(country: String, page: Int): Response<API_Response>

    suspend fun getSearchedNews(country: String, searchQuery: String, page: Int): Response<API_Response>

}