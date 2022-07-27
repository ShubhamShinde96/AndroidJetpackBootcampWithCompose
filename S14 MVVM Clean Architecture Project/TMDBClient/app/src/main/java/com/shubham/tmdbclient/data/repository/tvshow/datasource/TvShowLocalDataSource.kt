package com.shubham.tmdbclient.data.repository.tvshow.datasource

import com.shubham.tmdbclient.data.model.tv_show.TvShow

interface TvShowLocalDataSource {

    suspend fun getAllTvShowsFromDB(): List<TvShow>

    suspend fun saveTvShowsToDB(tvShows: List<TvShow>)

    suspend fun clearAll()

}