package com.shubham.tmdbclient.data.repository.movie

import android.util.Log
import com.shubham.tmdbclient.data.model.movie.Movie
import com.shubham.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.shubham.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.shubham.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.shubham.tmdbclient.domain.repository.MovieRepository
import java.lang.Exception

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
): MovieRepository {

    override suspend fun getMovies(): List<Movie>? {

        return getMoviesFromCache()
    }

    override suspend fun updateMovies(): List<Movie>? {

        val newListOfMovies = getMoviesFromApi()
        movieLocalDataSource.clearAll()
        movieLocalDataSource.saveMoviesToDB(newListOfMovies)
        movieCacheDataSource.saveMovieToCache(newListOfMovies)
        return newListOfMovies
    }

    suspend fun getMoviesFromApi(): List<Movie> {

        lateinit var movieList: List<Movie>

        try{
            val response = movieRemoteDataSource.getMovies()
            val body = response.body()

            if(body != null) {
                movieList = body.movies
            }

        }catch (e: Exception) {
            Log.i("TMDB_TAG", "Exception, MovieRepositoryImpl.kt, line no 35, E: $e")
        }

        return movieList
    }


    suspend fun getMoviesFromDB(): List<Movie> {

        lateinit var movieList: List<Movie>

        try {

            movieList = movieLocalDataSource.getMoviesFromDB()

        } catch (e: Exception) {
            Log.i("TMDB_TAG", "Exception, MovieRepositoryImpl.kt, line no 51, E: $e")
        }

        if(movieList.isNotEmpty()) {

            return movieList

        } else {

            movieList = getMoviesFromApi()
            movieLocalDataSource.saveMoviesToDB(movieList)
        }

        return movieList
    }

    suspend fun getMoviesFromCache(): List<Movie> {

        lateinit var movieList: List<Movie>

        try {

            movieList = movieCacheDataSource.getMoviesFromCache()

        } catch (e: Exception) {
            Log.i("TMDB_TAG", "Exception, MovieRepositoryImpl.kt, line no 76, E: $e")
        }

        if(movieList.isNotEmpty()) {

            return movieList

        } else {

            movieList = getMoviesFromDB()
            movieCacheDataSource.saveMovieToCache(movieList)
        }

        return movieList
    }

}