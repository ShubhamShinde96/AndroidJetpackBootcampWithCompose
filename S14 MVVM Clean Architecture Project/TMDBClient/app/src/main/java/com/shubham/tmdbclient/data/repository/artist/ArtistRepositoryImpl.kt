package com.shubham.tmdbclient.data.repository.artist

import android.util.Log
import com.shubham.tmdbclient.data.model.artist.Artist
import com.shubham.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.shubham.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.shubham.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.shubham.tmdbclient.domain.repository.ArtistsRepository
import java.lang.Exception

class ArtistRepositoryImpl(
    private val artistRemoteDataSource: ArtistRemoteDataSource,
    private val artistLocalDataSource: ArtistLocalDataSource,
    private val artistCacheDataSource: ArtistCacheDataSource
): ArtistsRepository {

    override suspend fun getArtists(): List<Artist>? {

        return getArtistsFromCache()
    }

    override suspend fun updateArtists(): List<Artist>? {

        val newListOfArtist = getArtistsFromApi()
        artistLocalDataSource.clearAll()
        artistLocalDataSource.saveArtistsToDB(newListOfArtist)
        artistCacheDataSource.setArtistsToCache(newListOfArtist)

        return newListOfArtist
    }


    suspend fun getArtistsFromApi(): List<Artist> {

        Log.i("TMDB_TAG", "35, inside getArtistsFromApi()")

        lateinit var artistsList: List<Artist>

        try {

            val response = artistRemoteDataSource.getArtists()
            Log.i("TMDB_TAG", "40 Artist Repo, Response: $response")
            Log.i("TMDB_TAG", "40 Artist Repo, Response.Body(): ${response.body()}")
            val body = response.body()

            if(body != null) {
                artistsList = body.artists
            }

        } catch (e: Exception) {
            Log.i("TMDB_TAG", "Exception, ArtistRepositoryImpl.kt, line no 40, E: $e")
        }

        return artistsList
    }

    suspend fun getArtistFromDB(): List<Artist> {

        lateinit var artistsList: List<Artist>

        try {

            artistsList = artistLocalDataSource.getArtistsFromDB()

        } catch (e: Exception) {

            Log.i("TMDB_TAG", "Exception, ArtistRepositoryImpl.kt, line no 51, E: $e")
        }

        if(artistsList.isNotEmpty()) {

            return artistsList

        } else {

            artistsList = getArtistsFromApi()
            artistLocalDataSource.saveArtistsToDB(artistsList)
        }

        return artistsList
    }

    suspend fun getArtistsFromCache(): List<Artist> {

        lateinit var artistsList: List<Artist>

        try {

            artistsList = artistCacheDataSource.getArtistsFromCache()

        } catch (e: Exception) {

            Log.i("TMDB_TAG", "Exception, ArtistsRepositoryImpl.kt, line no 76, E: $e")
        }

        if(artistsList.isNotEmpty()) {

            return artistsList

        } else {

            artistsList = getArtistFromDB()
            artistCacheDataSource.setArtistsToCache(artistsList)
        }

        return artistsList
    }

}