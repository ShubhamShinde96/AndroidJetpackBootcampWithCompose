package com.shubham.tmdbclient.data.repository.movie.datasourceimpl

import com.shubham.tmdbclient.data.model.movie.Movie
import com.shubham.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource

class MovieCacheDataSourceImpl: MovieCacheDataSource {

    // Here we're going to use a very basic caching mechanism, here we will create an arraylist of movie instances.
    // then whenever the user launches the app and load movie data for the first time, we will assign that list
    // to this arraylist, we will keep this MovieCacheDataSourceImpl instance which hold the arraylist as a singleton
    // using dagger, so if the use wants to load the movie list again instead of using the database we can just use
    // the cached list.

    private var movieList = ArrayList<Movie>()

    override suspend fun getMoviesFromCache(): List<Movie> {
        return movieList
    }

    override suspend fun saveMovieToCache(movies: List<Movie>) {
        movieList.clear()
        movieList = ArrayList(movies)
    }

}