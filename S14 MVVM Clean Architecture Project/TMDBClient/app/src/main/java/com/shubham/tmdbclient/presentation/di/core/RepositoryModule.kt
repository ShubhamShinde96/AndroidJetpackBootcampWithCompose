package com.shubham.tmdbclient.presentation.di.core

import com.shubham.tmdbclient.data.repository.artist.ArtistRepositoryImpl
import com.shubham.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.shubham.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.shubham.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.shubham.tmdbclient.data.repository.movie.MovieRepositoryImpl
import com.shubham.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.shubham.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.shubham.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.shubham.tmdbclient.data.repository.tvshow.TvShowRepositoryImpl
import com.shubham.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.shubham.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.shubham.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.shubham.tmdbclient.data.repository.tvshow.datasourceimpl.TvShowCacheDataSourceImpl
import com.shubham.tmdbclient.domain.repository.ArtistsRepository
import com.shubham.tmdbclient.domain.repository.MovieRepository
import com.shubham.tmdbclient.domain.repository.TvShowRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
        movieLocalDataSource: MovieLocalDataSource,
        movieCacheDataSource: MovieCacheDataSource
    ): MovieRepository {

        return MovieRepositoryImpl(movieRemoteDataSource, movieLocalDataSource, movieCacheDataSource)
    }


    @Singleton
    @Provides
    fun provideArtistRepository(
        artistRemoteDataSource: ArtistRemoteDataSource,
        artistLocalDataSource: ArtistLocalDataSource,
        artistCacheDataSource: ArtistCacheDataSource
    ): ArtistsRepository {

        return ArtistRepositoryImpl(artistRemoteDataSource, artistLocalDataSource, artistCacheDataSource)
    }


    @Singleton
    @Provides
    fun provideTvShowRepository(
        tvShowRemoteDataSource: TvShowRemoteDataSource,
        tvShowLocalDataSource: TvShowLocalDataSource,
        tvShowCacheDataSource: TvShowCacheDataSource
    ): TvShowRepository {

        return TvShowRepositoryImpl(tvShowRemoteDataSource, tvShowLocalDataSource, tvShowCacheDataSource)
    }

}