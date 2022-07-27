package com.shubham.tmdbclient.data.repository.tvshow.datasource

import com.shubham.tmdbclient.data.model.tv_show.TvShow

interface TvShowCacheDataSource {

    suspend fun getTvShowsFromCache(): List<TvShow>

    suspend fun saveTvShowsToCache(tvShows: List<TvShow>)

}