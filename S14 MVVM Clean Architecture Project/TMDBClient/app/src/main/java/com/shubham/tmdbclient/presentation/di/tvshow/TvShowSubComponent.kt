package com.shubham.tmdbclient.presentation.di.tvshow

import com.shubham.tmdbclient.presentation.artist.ArtistActivity
import com.shubham.tmdbclient.presentation.tvshow.TvShowActivity
import dagger.Subcomponent

@TvShowScope
@Subcomponent(modules = [TvShowModule::class])
interface TvShowSubComponent {

    // We'll use this subcomponent to inject dependencies to ArtistActivity, therefore we need to define an inject fun keeping,
    // an instance of ArtistActivity as a parameter

    fun inject(tvShowActivity: TvShowActivity)

    // We must define a subcomponent factory inside this ArtistComponent so that appcomponent knows how to create instances of this
    // artist component subcomponent
    // Following is the way how we do it

    @Subcomponent.Factory
    interface Factory {
        fun create(): TvShowSubComponent
    }
}