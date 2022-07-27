package com.shubham.tmdbclient.domain.use_cases

import com.shubham.tmdbclient.data.model.movie.Movie
import com.shubham.tmdbclient.domain.repository.MovieRepository

class UpdateMoviesUseCase(private val movieRepository: MovieRepository) {

    suspend fun execute(): List<Movie>? = movieRepository.updateMovies()

}