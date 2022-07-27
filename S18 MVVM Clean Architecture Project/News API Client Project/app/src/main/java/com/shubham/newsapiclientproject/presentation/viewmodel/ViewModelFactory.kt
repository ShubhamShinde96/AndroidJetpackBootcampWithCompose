package com.shubham.newsapiclientproject.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shubham.newsapiclientproject.domain.usecase.GetNewsHeadlineUseCase
import com.shubham.newsapiclientproject.domain.usecase.GetSavedNewsUseCase
import com.shubham.newsapiclientproject.domain.usecase.GetSearchedNewsUseCase
import com.shubham.newsapiclientproject.domain.usecase.SaveNewsUseCase

class ViewModelFactory(
    private val app: Application,
    private val getNewsHeadlineUseCase: GetNewsHeadlineUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return NewsViewModel(app, getNewsHeadlineUseCase, getSearchedNewsUseCase, saveNewsUseCase, getSavedNewsUseCase) as T
    }

}