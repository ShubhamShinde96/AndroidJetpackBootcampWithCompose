package com.shubham.tmdbclient.presentation.di.movie

import com.shubham.tmdbclient.domain.use_cases.GetArtistsUseCase
import com.shubham.tmdbclient.domain.use_cases.GetMoviesUseCase
import com.shubham.tmdbclient.domain.use_cases.UpdateArtistsUseCase
import com.shubham.tmdbclient.domain.use_cases.UpdateMoviesUseCase
import com.shubham.tmdbclient.presentation.artist.ArtistViewModelFactory
import com.shubham.tmdbclient.presentation.di.artist.ArtistScope
import com.shubham.tmdbclient.presentation.movie.MovieViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MovieModule {

    @MovieScope
    @Provides
    fun provideMovieViewModelFactory(
        getMoviesUseCase: GetMoviesUseCase,
        updateMoviesUseCase: UpdateMoviesUseCase
    ): MovieViewModelFactory {

        return MovieViewModelFactory(getMoviesUseCase, updateMoviesUseCase)
    }

}