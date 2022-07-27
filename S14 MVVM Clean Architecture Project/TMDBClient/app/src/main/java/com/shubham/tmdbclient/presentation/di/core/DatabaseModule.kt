package com.shubham.tmdbclient.presentation.di.core

import android.content.Context
import androidx.room.Room
import com.shubham.tmdbclient.db.ArtistDao
import com.shubham.tmdbclient.db.MovieDao
import com.shubham.tmdbclient.db.TMDBDatabase
import com.shubham.tmdbclient.db.TvShowDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideMovieDatabase(context: Context): TMDBDatabase {

        return Room.databaseBuilder(context, TMDBDatabase::class.java, "tmdbclient").build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(tmdbDatabase: TMDBDatabase): MovieDao {

        return tmdbDatabase.movieDao()
    }

    @Singleton
    @Provides
    fun provideArtistDao(tmdbDatabase: TMDBDatabase): ArtistDao {

        return tmdbDatabase.artistDao()
    }

    @Singleton
    @Provides
    fun provideTvShowDao(tmdbDatabase: TMDBDatabase): TvShowDao {

        return tmdbDatabase.tvShowDao()
    }

}