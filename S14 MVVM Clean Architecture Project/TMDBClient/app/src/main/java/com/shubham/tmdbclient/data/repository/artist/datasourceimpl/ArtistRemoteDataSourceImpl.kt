package com.shubham.tmdbclient.data.repository.artist.datasourceimpl

import com.shubham.tmdbclient.data.api.TMDBService
import com.shubham.tmdbclient.data.model.artist.ArtistList
import com.shubham.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import retrofit2.Response

class ArtistRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey: String
): ArtistRemoteDataSource {

    override suspend fun getArtists(): Response<ArtistList> = tmdbService.getPopularArtists(apiKey)


}