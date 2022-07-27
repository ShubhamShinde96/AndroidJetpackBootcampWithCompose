package com.shubham.newsapiclientproject.data.repository

import com.shubham.newsapiclientproject.data.model.API_Response
import com.shubham.newsapiclientproject.data.model.Article
import com.shubham.newsapiclientproject.data.repository.dataSource.NewsLocalDataSource
import com.shubham.newsapiclientproject.data.repository.dataSource.NewsRemoteDataSource
import com.shubham.newsapiclientproject.data.util.Resource
import com.shubham.newsapiclientproject.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
): NewsRepository {

    //                      Notice the return type is "Resource" instance of API_Response
    override suspend fun getNewsHeadlines(country: String, page: Int): Resource<API_Response> {

        return responseToResource(newsRemoteDataSource.getTopHeadline(country, page))
    }

    override suspend fun getSearchedNews(
        country: String,
        searchQuery: String,
        page: Int
    ): Resource<API_Response> {

        return responseToResource(newsRemoteDataSource.getSearchedNews(country, searchQuery, page))
    }

    private fun responseToResource(response: Response<API_Response>): Resource<API_Response> {

        if(response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }

        return Resource.Error(response.message())
    }



    override suspend fun saveNews(article: Article) {
        newsLocalDataSource.saveArticleToDB(article)
    }

    override suspend fun deleteNews(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<List<Article>> {

        return newsLocalDataSource.getSavedArticles()
    }
}