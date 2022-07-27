package com.shubham.tmdbclient.data.repository.artist.datasource

import com.shubham.tmdbclient.data.model.artist.Artist

interface ArtistCacheDataSource {

    suspend fun getArtistsFromCache(): List<Artist>

    suspend fun setArtistsToCache(artists: List<Artist>)

}