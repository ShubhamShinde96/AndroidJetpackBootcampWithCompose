package com.shubham.tmdbclient.data.repository.artist.datasourceimpl

import com.shubham.tmdbclient.data.model.artist.Artist
import com.shubham.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.shubham.tmdbclient.db.ArtistDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistLocalDataSourceImpl(private val artistDao: ArtistDao) : ArtistLocalDataSource {

    override suspend fun getArtistsFromDB(): List<Artist> {
        return artistDao.getAllArtists()
    }

    override suspend fun saveArtistsToDB(artists: List<Artist>) {

        CoroutineScope(Dispatchers.IO).launch {
            artistDao.insertArtists(artists)
        }
    }

    override suspend fun clearAll() {

        CoroutineScope(Dispatchers.IO).launch {
            artistDao.deleteAllArtists()
        }
    }

}