package com.shubham.tmdbclient.presentation.di.core

import com.shubham.tmdbclient.domain.repository.ArtistsRepository
import com.shubham.tmdbclient.domain.repository.MovieRepository
import com.shubham.tmdbclient.domain.repository.TvShowRepository
import com.shubham.tmdbclient.domain.use_cases.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    fun provideGetMovieUseCase(movieRepository: MovieRepository): GetMoviesUseCase {

        return GetMoviesUseCase(movieRepository)
    }

    @Provides
    fun provideUpdateMovieUseCase(movieRepository: MovieRepository): UpdateMoviesUseCase {

        return UpdateMoviesUseCase(movieRepository)
    }


    @Provides
    fun provideGetTvShowUseCase(tvShowRepository: TvShowRepository): GetTvShowsUseCase {

        return GetTvShowsUseCase(tvShowRepository)
    }

    @Provides
    fun provideUpdateTvShowUseCase(tvShowRepository: TvShowRepository): UpdateTvShowUseCase {

        return UpdateTvShowUseCase(tvShowRepository)
    }


    @Provides
    fun provideGetArtistUseCase(artistsRepository: ArtistsRepository): GetArtistsUseCase {

        return GetArtistsUseCase(artistsRepository)
    }

    @Provides
    fun provideUpdateArtistUseCase(artistsRepository: ArtistsRepository): UpdateArtistsUseCase {

        return UpdateArtistsUseCase(artistsRepository)
    }

}