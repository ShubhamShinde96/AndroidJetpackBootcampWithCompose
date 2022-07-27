package com.shubham.tmdbclient.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shubham.tmdbclient.data.model.artist.Artist
import com.shubham.tmdbclient.data.model.movie.Movie
import com.shubham.tmdbclient.data.model.tv_show.TvShow

@Database(
    entities = [Artist::class, TvShow::class, Movie::class],
    version = 1,
    exportSchema = false
)
abstract class TMDBDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    abstract fun tvShowDao(): TvShowDao

    abstract fun artistDao(): ArtistDao

}