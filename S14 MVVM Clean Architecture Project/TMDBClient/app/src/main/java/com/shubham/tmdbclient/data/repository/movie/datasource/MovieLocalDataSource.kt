package com.shubham.tmdbclient.data.repository.movie.datasource

import com.shubham.tmdbclient.data.model.movie.Movie

interface MovieLocalDataSource {

    suspend fun getMoviesFromDB(): List<Movie>

    suspend fun saveMoviesToDB(movies: List<Movie>)

    suspend fun clearAll()

}