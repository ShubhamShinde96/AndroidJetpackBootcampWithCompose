package com.shubham.newsapiclientproject.domain.usecase

import com.shubham.newsapiclientproject.data.model.Article
import com.shubham.newsapiclientproject.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {

    // We have not used suspend here, cause we are using Flow for getting the data
    fun execute(): Flow<List<Article>>{
        return newsRepository.getSavedNews()
    }

}