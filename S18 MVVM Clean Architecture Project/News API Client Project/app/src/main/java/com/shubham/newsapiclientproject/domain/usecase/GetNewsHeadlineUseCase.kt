package com.shubham.newsapiclientproject.domain.usecase

import com.shubham.newsapiclientproject.data.model.API_Response
import com.shubham.newsapiclientproject.data.util.Resource
import com.shubham.newsapiclientproject.domain.repository.NewsRepository

class GetNewsHeadlineUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(country: String, page: Int): Resource<API_Response> {

        return newsRepository.getNewsHeadlines(country, page)
    }

}