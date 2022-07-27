package com.shubham.tmdbclient.data.repository.movie.datasourceimpl

import com.shubham.tmdbclient.data.model.movie.Movie
import com.shubham.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.shubham.tmdbclient.db.MovieDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieLocalDataSourceImpl(private val movieDao: MovieDao): MovieLocalDataSource {

    override suspend fun getMoviesFromDB(): List<Movie> {
        return movieDao.getAllMovies()
    }

    override suspend fun saveMoviesToDB(movies: List<Movie>) {

        CoroutineScope(Dispatchers.IO).launch {
            movieDao.saveMovies(movies)
        }
    }

    override suspend fun clearAll() {

        CoroutineScope(Dispatchers.IO).launch {
            movieDao.deleteAllMovies()
        }
    }


}