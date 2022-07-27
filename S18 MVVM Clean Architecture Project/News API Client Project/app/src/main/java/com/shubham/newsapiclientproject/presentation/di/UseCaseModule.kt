package com.anushka.newsapiclient.presentation.di

import com.shubham.newsapiclientproject.domain.repository.NewsRepository
import com.shubham.newsapiclientproject.domain.usecase.GetNewsHeadlineUseCase
import com.shubham.newsapiclientproject.domain.usecase.GetSavedNewsUseCase
import com.shubham.newsapiclientproject.domain.usecase.GetSearchedNewsUseCase
import com.shubham.newsapiclientproject.domain.usecase.SaveNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
   @Singleton
   @Provides
   fun provideGetNewsheadLinesUseCase(
       newsRepository: NewsRepository
   ):GetNewsHeadlineUseCase{
      return GetNewsHeadlineUseCase(newsRepository)
   }

   @Singleton
   @Provides
   fun provideGetSearchedNewsUseCase(
      newsRepository: NewsRepository
   ):GetSearchedNewsUseCase{
      return GetSearchedNewsUseCase(newsRepository)
   }

   @Singleton
   @Provides
   fun provideSaveNewsUseCase(
      newsRepository: NewsRepository
   ):SaveNewsUseCase{
      return SaveNewsUseCase(newsRepository)
   }

   @Singleton
   @Provides
   fun provideGetSavedNewsUseCase(
      newsRepository: NewsRepository
   ):GetSavedNewsUseCase{
      return GetSavedNewsUseCase(newsRepository)
   }

}


















