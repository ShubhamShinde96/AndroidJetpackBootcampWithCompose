package com.shubham.tmdbclient.presentation.di.core

import com.shubham.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.shubham.tmdbclient.data.repository.artist.datasourceimpl.ArtistCacheDataSourceImpl
import com.shubham.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.shubham.tmdbclient.data.repository.movie.datasourceimpl.MovieCacheDataSourceImpl
import com.shubham.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.shubham.tmdbclient.data.repository.tvshow.datasourceimpl.TvShowCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataSourceModule {

    @Singleton
    @Provides
    fun provideMovieCacheDataSource(): MovieCacheDataSource {

        // We could have used @inject for the constructor of MovieCacheDataSourceImpl and used that dependency here as a parameter
        // why we didn't do that? !! need to find out

        return MovieCacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideArtistCacheDataSource(): ArtistCacheDataSource {

        return ArtistCacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideTvShowCacheDataSource(): TvShowCacheDataSource {

        return TvShowCacheDataSourceImpl()
    }

}