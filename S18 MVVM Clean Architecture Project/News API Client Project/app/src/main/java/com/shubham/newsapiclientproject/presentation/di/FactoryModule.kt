package com.anushka.newsapiclient.presentation.di

import android.app.Application
import com.shubham.newsapiclientproject.domain.usecase.GetNewsHeadlineUseCase
import com.shubham.newsapiclientproject.domain.usecase.GetSavedNewsUseCase
import com.shubham.newsapiclientproject.domain.usecase.GetSearchedNewsUseCase
import com.shubham.newsapiclientproject.domain.usecase.SaveNewsUseCase
import com.shubham.newsapiclientproject.presentation.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideNewsViewModelFactory(
        application: Application,
        getNewsHeadlinesUseCase: GetNewsHeadlineUseCase,
        getSearchedNewsUseCase: GetSearchedNewsUseCase,
        saveNewsUseCase: SaveNewsUseCase,
        getSavedNewsUseCase: GetSavedNewsUseCase
    ): ViewModelFactory {
        return ViewModelFactory(
            application,
            getNewsHeadlinesUseCase,
            getSearchedNewsUseCase,
            saveNewsUseCase,
            getSavedNewsUseCase
        )
    }
}








