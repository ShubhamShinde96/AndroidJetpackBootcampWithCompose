package com.shubham.tmdbclient.domain.repository

import com.shubham.tmdbclient.data.model.tv_show.TvShow

interface TvShowRepository {

    suspend fun getTvShows(): List<TvShow>?

    suspend fun updateTvShows(): List<TvShow>?

}