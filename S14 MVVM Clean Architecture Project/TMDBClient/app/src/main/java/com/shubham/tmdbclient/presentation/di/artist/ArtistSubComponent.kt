package com.shubham.tmdbclient.presentation.di.artist

import com.shubham.tmdbclient.presentation.artist.ArtistActivity
import dagger.Subcomponent

@ArtistScope
@Subcomponent(modules = [ArtistModule::class])
interface ArtistSubComponent {

    // We'll use this subcomponent to inject dependencies to ArtistActivity, therefore we need to define an inject fun keeping,
    // an instance of ArtistActivity as a parameter

    fun inject(artistActivity: ArtistActivity)

    // We must define a subcomponent factory inside this ArtistComponent so that AppComponent knows how to create instances of this
    // artist component subcomponent
    // Following is the way how we do it

    @Subcomponent.Factory
    interface Factory {  // We're defining an interface inside interface.
        fun create(): ArtistSubComponent
    }
}