package com.shubham.newsapiclientproject.domain.usecase

import com.shubham.newsapiclientproject.data.model.Article
import com.shubham.newsapiclientproject.domain.repository.NewsRepository

class DeleteSavedNewsUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(article: Article) = newsRepository.deleteNews(article)

}