package com.shubham.newsapiclientproject.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.shubham.newsapiclientproject.data.model.API_Response
import com.shubham.newsapiclientproject.data.model.Article
import com.shubham.newsapiclientproject.data.util.Resource
import com.shubham.newsapiclientproject.domain.usecase.GetNewsHeadlineUseCase
import com.shubham.newsapiclientproject.domain.usecase.GetSavedNewsUseCase
import com.shubham.newsapiclientproject.domain.usecase.GetSearchedNewsUseCase
import com.shubham.newsapiclientproject.domain.usecase.SaveNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

class NewsViewModel(
    private val app: Application,
    private val getNewsHeadlineUseCase: GetNewsHeadlineUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase
): AndroidViewModel(app) { // We have used AndroidViewModel here instead of ViewModel, cause we required the context for fun isNetworkAvailable()

    val newsHeadlines: MutableLiveData<Resource<API_Response>> = MutableLiveData()

    fun getNewsHeadlines(country: String, page: Int) = viewModelScope.launch(Dispatchers.IO) {

        newsHeadlines.postValue(Resource.Loading())

        try {
            if (isNetworkAvailable(app)) {
                val apiResult = getNewsHeadlineUseCase.execute(country, page)
                newsHeadlines.postValue(apiResult)
            } else {
                newsHeadlines.postValue(Resource.Error("Internet is not available."))
            }
        } catch (e: Exception) {
            newsHeadlines.postValue(Resource.Error(e.message.toString()))
        }
    }


    private fun isNetworkAvailable(context: Context): Boolean {

        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }


    // Search
    val searchedNews: MutableLiveData<Resource<API_Response>> = MutableLiveData()

    fun searchNews(country: String, searchQuery: String, page: Int) = viewModelScope.launch {

        searchedNews.postValue(Resource.Loading())

        try {
            if (isNetworkAvailable(app)) {

                val response = getSearchedNewsUseCase.execute(country, searchQuery, page)

                searchedNews.postValue(response)

            } else {
                searchedNews.postValue(Resource.Error("Internet is not available."))
            }

        }catch (e: Exception) {

            searchedNews.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun saveArticle(article: Article) = viewModelScope.launch {
        saveNewsUseCase.execute(article)
    }

    @InternalCoroutinesApi
    fun getSavedNews() = liveData {

        getSavedNewsUseCase.execute().collect {
            emit(it)
        }
    }

}