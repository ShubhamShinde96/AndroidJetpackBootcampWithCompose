package com.shubham.tmdbclient.data.repository.movie.datasource

import com.shubham.tmdbclient.data.model.movie.Movie

interface MovieCacheDataSource {

    suspend fun getMoviesFromCache(): List<Movie>

    suspend fun saveMovieToCache(movies: List<Movie>)

}