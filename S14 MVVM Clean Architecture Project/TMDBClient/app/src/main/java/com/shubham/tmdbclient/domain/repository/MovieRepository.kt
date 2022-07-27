package com.shubham.tmdbclient.domain.repository

import com.shubham.tmdbclient.data.model.movie.Movie

interface MovieRepository {

    suspend fun getMovies(): List<Movie>?
    // The implementation impl class will implement this method, our useCase class don't need to know about the implementation of this method.

    suspend fun updateMovies(): List<Movie>?

    // These repository interfaces will be implemented in the data layer & these useCases will be executed
    // from the ViewModel of the presentation layer.

}