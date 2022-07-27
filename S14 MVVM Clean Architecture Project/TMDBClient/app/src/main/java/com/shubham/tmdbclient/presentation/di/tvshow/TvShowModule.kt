package com.shubham.tmdbclient.presentation.di.tvshow

import com.shubham.tmdbclient.domain.use_cases.*
import com.shubham.tmdbclient.presentation.artist.ArtistViewModelFactory
import com.shubham.tmdbclient.presentation.di.artist.ArtistScope
import com.shubham.tmdbclient.presentation.movie.MovieViewModelFactory
import com.shubham.tmdbclient.presentation.tvshow.TvShowViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TvShowModule {

    @TvShowScope
    @Provides
    fun provideTvShowViewModelFactory(
        getTvShowsUseCase: GetTvShowsUseCase,
        updateTvShowUseCase: UpdateTvShowUseCase
    ): TvShowViewModelFactory {

        return TvShowViewModelFactory(getTvShowsUseCase, updateTvShowUseCase)
    }

}