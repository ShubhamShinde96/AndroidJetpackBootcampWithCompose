package com.shubham.tmdbclient.data.repository.tvshow

import android.util.Log
import com.shubham.tmdbclient.data.model.tv_show.TvShow
import com.shubham.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.shubham.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.shubham.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.shubham.tmdbclient.domain.repository.TvShowRepository
import java.lang.Exception

class TvShowRepositoryImpl(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource,
    private val tvShowLocalDataSource: TvShowLocalDataSource,
    private val tvShowCacheDataSource: TvShowCacheDataSource
): TvShowRepository {

    override suspend fun getTvShows(): List<TvShow>? {

        return getTvShowsFromCache()
    }

    override suspend fun updateTvShows(): List<TvShow>? {

        val newTvShowsList = getTvShowsFromApi()

        tvShowLocalDataSource.clearAll()
        tvShowLocalDataSource.saveTvShowsToDB(newTvShowsList)
        tvShowCacheDataSource.saveTvShowsToCache(newTvShowsList)

        return newTvShowsList
    }

    suspend fun getTvShowsFromApi(): List<TvShow> {

        lateinit var tvShowsList: List<TvShow>

        try {

            val response = tvShowRemoteDataSource.getTvShows()
            val body = response.body()

            if(body != null) {

                tvShowsList = body.tvShows
            }

        } catch (e: Exception) {
            Log.i("TMDB_TAG", "Exception, TvShowRepositoryImpl.kt, line no 35, E: $e")
        }

        return tvShowsList
    }

    suspend fun getTvShowsFromDB(): List<TvShow> {

        lateinit var tvShowsList: List<TvShow>

        try {

            tvShowsList = tvShowLocalDataSource.getAllTvShowsFromDB()

        } catch (e: Exception) {

            Log.i("TMDB_TAG", "Exception, TvShowRepositoryImpl.kt, line no 51, E: $e")
        }

        if(tvShowsList.isNotEmpty()) {

            return tvShowsList
        } else {

            tvShowsList = getTvShowsFromApi()
            tvShowLocalDataSource.saveTvShowsToDB(tvShowsList)
        }

        return tvShowsList
    }

    suspend fun getTvShowsFromCache(): List<TvShow> {

        lateinit var tvShowsList: List<TvShow>

        try {

            tvShowsList = tvShowCacheDataSource.getTvShowsFromCache()

        } catch (e: Exception) {
            Log.i("TMDB_TAG", "Exception, TvShowRepositoryImpl.kt, line no 76, E: $e")
        }

        if(tvShowsList.isNotEmpty()) {

            return tvShowsList
        } else {

            tvShowsList = getTvShowsFromDB()
            tvShowCacheDataSource.saveTvShowsToCache(tvShowsList)
        }

        return tvShowsList
    }

}