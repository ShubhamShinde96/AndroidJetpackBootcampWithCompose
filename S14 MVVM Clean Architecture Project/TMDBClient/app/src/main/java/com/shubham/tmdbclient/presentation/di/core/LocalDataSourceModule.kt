package com.shubham.tmdbclient.presentation.di.core

import com.shubham.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.shubham.tmdbclient.data.repository.artist.datasourceimpl.ArtistLocalDataSourceImpl
import com.shubham.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.shubham.tmdbclient.data.repository.movie.datasourceimpl.MovieLocalDataSourceImpl
import com.shubham.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.shubham.tmdbclient.data.repository.tvshow.datasourceimpl.TvShowLocalDataSourceImpl
import com.shubham.tmdbclient.db.ArtistDao
import com.shubham.tmdbclient.db.MovieDao
import com.shubham.tmdbclient.db.TvShowDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataSourceModule() {

    @Singleton
    @Provides
    fun provideMovieLocalDataSource(movieDao: MovieDao): MovieLocalDataSource {

        return MovieLocalDataSourceImpl(movieDao)
    }

    @Singleton
    @Provides
    fun provideArtistLocalDataSource(artistDao: ArtistDao): ArtistLocalDataSource {

        return ArtistLocalDataSourceImpl(artistDao)
    }

    @Singleton
    @Provides
    fun provideTvShowLocalDataSource(tvShowDao: TvShowDao): TvShowLocalDataSource {

        return TvShowLocalDataSourceImpl(tvShowDao)
    }


}




























































