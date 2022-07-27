package com.shubham.newsapiclientproject.data.repository.dataSourceImpl

import com.shubham.newsapiclientproject.data.api.NewsAPIService
import com.shubham.newsapiclientproject.data.model.API_Response
import com.shubham.newsapiclientproject.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsAPIService: NewsAPIService,
) : NewsRemoteDataSource {

    override suspend fun getTopHeadline(country: String, page: Int): Response<API_Response> {
        return newsAPIService.getTopHeadlines(country, page)
    }

    override suspend fun getSearchedNews(
        country: String,
        searchQuery: String,
        page: Int
    ): Response<API_Response> {
        return newsAPIService.getSearchedTopHeadlines(country, searchQuery, page)
    }

}