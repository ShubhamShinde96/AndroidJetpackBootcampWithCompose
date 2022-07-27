package com.shubham.newsapiclientproject.domain.usecase

import com.shubham.newsapiclientproject.data.model.API_Response
import com.shubham.newsapiclientproject.data.util.Resource
import com.shubham.newsapiclientproject.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(country: String, searchQuery: String, page: Int): Resource<API_Response> {

        return newsRepository.getSearchedNews(country, searchQuery, page)
    }

}