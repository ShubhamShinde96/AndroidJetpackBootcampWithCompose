package com.shubham.tmdbclient.data.repository.tvshow.datasourceimpl

import com.shubham.tmdbclient.data.model.tv_show.TvShow
import com.shubham.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.shubham.tmdbclient.db.TvShowDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvShowLocalDataSourceImpl(private val tvShowDao: TvShowDao): TvShowLocalDataSource {

    override suspend fun getAllTvShowsFromDB(): List<TvShow> {
        return tvShowDao.getAllTvShows()
    }

    override suspend fun saveTvShowsToDB(tvShows: List<TvShow>) {

        CoroutineScope(Dispatchers.IO).launch {
            tvShowDao.insertTvShows(tvShows)
        }
    }

    override suspend fun clearAll() {

        CoroutineScope(Dispatchers.IO).launch {
            tvShowDao.deleteAllTvShows()
        }
    }
}