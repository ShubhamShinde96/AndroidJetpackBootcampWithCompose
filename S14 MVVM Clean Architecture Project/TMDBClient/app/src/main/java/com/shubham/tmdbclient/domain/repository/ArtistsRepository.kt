package com.shubham.tmdbclient.domain.repository

import com.shubham.tmdbclient.data.model.artist.Artist

interface ArtistsRepository {

    suspend fun getArtists(): List<Artist>?

    suspend fun updateArtists(): List<Artist>?

}