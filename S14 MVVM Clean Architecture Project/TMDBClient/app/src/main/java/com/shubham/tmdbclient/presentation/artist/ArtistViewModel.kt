package com.shubham.tmdbclient.presentation.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.shubham.tmdbclient.domain.use_cases.GetArtistsUseCase
import com.shubham.tmdbclient.domain.use_cases.UpdateArtistsUseCase

class ArtistViewModel(
    private val getArtistsUseCase: GetArtistsUseCase,
    private val updateArtistsUseCase: UpdateArtistsUseCase
): ViewModel() {

    fun getArtists() = liveData {

        val artistList = getArtistsUseCase.execute()
        emit(artistList)
    }

    fun updateArtists() = liveData {

        val artistsList = updateArtistsUseCase.execute()
        emit(artistsList)
    }

}