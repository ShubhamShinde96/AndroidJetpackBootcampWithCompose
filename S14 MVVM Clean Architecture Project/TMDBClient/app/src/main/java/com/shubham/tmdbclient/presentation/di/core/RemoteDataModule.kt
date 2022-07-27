package com.shubham.tmdbclient.presentation.di.core

import com.shubham.tmdbclient.data.api.TMDBService
import com.shubham.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.shubham.tmdbclient.data.repository.artist.datasourceimpl.ArtistRemoteDataSourceImpl
import com.shubham.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.shubham.tmdbclient.data.repository.movie.datasourceimpl.MovieRemoteDataSourceImpl
import com.shubham.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.shubham.tmdbclient.data.repository.tvshow.datasourceimpl.TvShowRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule(private val apiKey: String) {

    @Singleton
    @Provides // here we need tmdbservice dependency, dagger will provide this dependency to this module.
    fun provideMovieRemoteDataSource(tmdbService: TMDBService): MovieRemoteDataSource  {

        return MovieRemoteDataSourceImpl(tmdbService, apiKey)
    }


    @Singleton
    @Provides
    fun provideTvShowRemoteDataSource(tmdbService: TMDBService): TvShowRemoteDataSource {

        return TvShowRemoteDataSourceImpl(tmdbService, apiKey)
    }

    @Singleton
    @Provides
    fun provideArtistRemoteDataSource(tmdbService: TMDBService): ArtistRemoteDataSource {

        return ArtistRemoteDataSourceImpl(tmdbService, apiKey)
    }



}



































