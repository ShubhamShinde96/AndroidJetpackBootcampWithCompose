package com.shubham.newsapiclientproject.data.repository.dataSource

import com.shubham.newsapiclientproject.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {

    suspend fun saveArticleToDB(article: Article)

    fun getSavedArticles(): Flow<List<Article>>

}