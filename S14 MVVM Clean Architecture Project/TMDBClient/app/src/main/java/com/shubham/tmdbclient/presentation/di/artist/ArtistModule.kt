package com.shubham.tmdbclient.presentation.di.artist

import com.shubham.tmdbclient.domain.use_cases.GetArtistsUseCase
import com.shubham.tmdbclient.domain.use_cases.UpdateArtistsUseCase
import com.shubham.tmdbclient.presentation.artist.ArtistViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ArtistModule {

    @ArtistScope
    @Provides
    fun provideArtistViewModelFactory(
        getArtistsUseCase: GetArtistsUseCase,
        updateArtistsUseCase: UpdateArtistsUseCase
    ): ArtistViewModelFactory {

        return ArtistViewModelFactory(getArtistsUseCase, updateArtistsUseCase)
    }

}