package com.shubham.tmdbclient.data.repository.artist.datasource

import com.shubham.tmdbclient.data.model.artist.ArtistList
import retrofit2.Response

interface ArtistRemoteDataSource {

    suspend fun getArtists(): Response<ArtistList>

}