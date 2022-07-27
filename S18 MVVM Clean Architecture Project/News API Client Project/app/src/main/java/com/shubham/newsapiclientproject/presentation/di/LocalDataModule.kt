package com.shubham.newsapiclientproject.presentation.di

import com.shubham.newsapiclientproject.data.db.ArticleDAO
import com.shubham.newsapiclientproject.data.repository.dataSource.NewsLocalDataSource
import com.shubham.newsapiclientproject.data.repository.dataSourceImpl.NewsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(articleDAO: ArticleDAO): NewsLocalDataSource {
        return NewsLocalDataSourceImpl(articleDAO)
    }

}