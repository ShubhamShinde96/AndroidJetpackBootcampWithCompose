package com.anushka.newsapiclient.presentation.di

import com.shubham.newsapiclientproject.data.api.NewsAPIService
import com.shubham.newsapiclientproject.data.repository.dataSource.NewsRemoteDataSource
import com.shubham.newsapiclientproject.data.repository.dataSourceImpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(
        newsAPIService: NewsAPIService
    ):NewsRemoteDataSource{
       return NewsRemoteDataSourceImpl(newsAPIService)
    }

}












