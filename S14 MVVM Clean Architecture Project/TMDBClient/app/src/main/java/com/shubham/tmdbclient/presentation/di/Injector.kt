package com.shubham.tmdbclient.presentation.di

import com.shubham.tmdbclient.presentation.di.artist.ArtistSubComponent
import com.shubham.tmdbclient.presentation.di.movie.MovieSubComponent
import com.shubham.tmdbclient.presentation.di.tvshow.TvShowSubComponent

interface Injector {

    fun createMovieSubComponent(): MovieSubComponent

    fun createTvShowSubComponent(): TvShowSubComponent

    fun createArtistSubComponent(): ArtistSubComponent

    // Now lets create App class

}