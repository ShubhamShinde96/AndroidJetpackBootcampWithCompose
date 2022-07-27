package com.shubham.tmdbclient.db

import androidx.room.*
import com.shubham.tmdbclient.data.model.tv_show.TvShow
import kotlinx.coroutines.flow.Flow

@Dao
interface TvShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShows(tvShows: List<TvShow>)

    @Query("DELETE FROM popular_tvShows")
    suspend fun deleteAllTvShows()

    @Query("SELECT * FROM POPULAR_TVSHOWS")
    suspend fun getAllTvShows(): List<TvShow>

}