package com.shubham.tmdbclient.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shubham.tmdbclient.data.model.artist.Artist
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtists(artists: List<Artist>)

    @Query("DELETE FROM popular_artist")
    suspend fun deleteAllArtists()

    @Query("SELECT * FROM popular_artist")
    suspend fun getAllArtists(): List<Artist>

}