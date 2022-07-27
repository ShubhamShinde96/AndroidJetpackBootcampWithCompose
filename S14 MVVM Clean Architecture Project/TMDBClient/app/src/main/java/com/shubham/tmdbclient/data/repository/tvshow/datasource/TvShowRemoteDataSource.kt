package com.shubham.tmdbclient.data.repository.tvshow.datasource

import com.shubham.tmdbclient.data.model.tv_show.TvShowList
import retrofit2.Response

interface TvShowRemoteDataSource {

    suspend fun getTvShows(): Response<TvShowList>

}