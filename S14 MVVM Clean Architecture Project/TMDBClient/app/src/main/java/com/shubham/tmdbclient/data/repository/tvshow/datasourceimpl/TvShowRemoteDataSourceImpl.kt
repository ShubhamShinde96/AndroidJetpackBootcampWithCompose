package com.shubham.tmdbclient.data.repository.tvshow.datasourceimpl

import com.shubham.tmdbclient.data.api.TMDBService
import com.shubham.tmdbclient.data.model.tv_show.TvShowList
import com.shubham.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import retrofit2.Response

class TvShowRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey: String
) : TvShowRemoteDataSource {

    override suspend fun getTvShows(): Response<TvShowList> = tmdbService.getPopularTvShows(apiKey)

}