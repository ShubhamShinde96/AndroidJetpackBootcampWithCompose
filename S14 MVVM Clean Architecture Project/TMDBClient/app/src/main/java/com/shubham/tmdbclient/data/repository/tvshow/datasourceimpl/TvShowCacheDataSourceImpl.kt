package com.shubham.tmdbclient.data.repository.tvshow.datasourceimpl

import com.shubham.tmdbclient.data.model.tv_show.TvShow
import com.shubham.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource

class TvShowCacheDataSourceImpl: TvShowCacheDataSource {

    private var tvShowsList = ArrayList<TvShow>()

    override suspend fun getTvShowsFromCache(): List<TvShow> {
        return tvShowsList
    }

    override suspend fun saveTvShowsToCache(tvShows: List<TvShow>) {
        tvShowsList.clear()
        tvShowsList = ArrayList(tvShows)
    }
}