package com.shubham.tmdbclient.data.repository.movie.datasource

import com.shubham.tmdbclient.data.model.movie.MovieList
import retrofit2.Response

interface MovieRemoteDataSource {

    suspend fun getMovies(): Response<MovieList>

    // In clean architecture we always use public interfaces to communicate between components, hence this
    // interface is created, along with it the local data source and cash data source is going to be created.

}




































