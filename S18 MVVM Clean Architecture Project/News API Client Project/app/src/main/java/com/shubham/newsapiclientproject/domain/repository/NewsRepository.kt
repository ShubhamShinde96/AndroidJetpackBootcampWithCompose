package com.shubham.newsapiclientproject.domain.repository

import androidx.lifecycle.LiveData
import com.shubham.newsapiclientproject.data.model.API_Response
import com.shubham.newsapiclientproject.data.model.Article
import com.shubham.newsapiclientproject.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNewsHeadlines(country: String, page: Int): Resource<API_Response>

    suspend fun getSearchedNews(country: String, searchQuery: String, page: Int): Resource<API_Response>

    suspend fun saveNews(article: Article)

    suspend fun deleteNews(article: Article)

    //  fun getSavedNews(): LiveData<List<Article>>
    //  We can use LiveData like this, but this is not the best practice for MVVM architecture,
    //  because LiveData is lifecycle aware observable data holder class, we should always try to use livedata
    //  in ViewModels and observe them from Activities and Fragments, but it is not recommended to use LiveData in repositories, because that will cause unexpected threading issues.
    //  Before kotlin coroutine, most of the developers used RxJava to get data from the repositories to the ViewModel
    //  and then used LiveData to emit that data from the ViewModel to Activities and Fragments
    //  but now we have coroutines Flow API
    //  Flow API is a better way to handle a stream of data asynchronously, Room library allows us to get the data
    //  as a Flow
    fun getSavedNews(): Flow<List<Article>> // We will get the list of data from the db table as a flow
    //  In the ViewModel class we will collect this stream of data flow and emit it as a LiveData
    //  Since this function return data stream Flow which is part of coroutine we don't need to write this fun as a suspending fun.
    //  We don't want to pause or resume this function.

    //  In clean architecture we do not use Android Framework related libraries in the domain layer, that means in
    //  repository interface and in use case classes we can only import kotlin language related classes and our own classes.
    //  As you can see we haven't used any AndroidX libraries here, see the package imports.

}