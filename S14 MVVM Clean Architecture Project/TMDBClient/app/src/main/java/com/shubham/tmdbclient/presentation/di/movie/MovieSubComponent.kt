package com.shubham.tmdbclient.presentation.di.movie

import com.shubham.tmdbclient.presentation.artist.ArtistActivity
import com.shubham.tmdbclient.presentation.movie.MovieActivity
import dagger.Subcomponent

@MovieScope
@Subcomponent(modules = [MovieModule::class])
interface MovieSubComponent {


    // We use @Singleton annotation to reuse the module throughout the app without recreating again and again
    // as we're going to use repositories throughout the app but in some cases we need to limit the scope to
    // the lifecycle of the activity, we don't want the instance access throughout the app for some case
    // for ex: We're going to use MovieViewModel, ArtistViewModel & TvShowViewModel only in their respective
    // activities so here we want the scope to be limited to the lifecycle of their respective activities.
    // And that is the reason we are creating these SubComponents: MovieSubComponent, ArtistSubComponent &
    // TvShowSubComponent

    // We'll use this subcomponent to inject dependencies to ArtistActivity, therefore we need to define an inject fun keeping,
    // an instance of ArtistActivity as a parameter

    fun inject(movieActivity: MovieActivity)

    // We must define a subcomponent factory inside this ArtistComponent so that appcomponent knows how to create instances of this
    // movie component subcomponent
    // Following is the way how we do it

    @Subcomponent.Factory
    interface Factory {
        fun create(): MovieSubComponent
    }

//    If a subcomponent defines a factory, its parent component(s) will have a binding for that factory type,
//    allowing an instance of that factory to be injected or returned from a method on that component like
//    any other binding.


}






























