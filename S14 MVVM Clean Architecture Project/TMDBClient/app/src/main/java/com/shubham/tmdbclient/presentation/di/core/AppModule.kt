package com.shubham.tmdbclient.presentation.di.core

import android.content.Context
import com.shubham.tmdbclient.presentation.di.artist.ArtistSubComponent
import com.shubham.tmdbclient.presentation.di.movie.MovieSubComponent
import com.shubham.tmdbclient.presentation.di.tvshow.TvShowSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

// here we need to list all the subcomponents with the module annotation.
@Module(subcomponents = [MovieSubComponent::class, TvShowSubComponent::class, ArtistSubComponent::class])
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideApplicationContext(): Context {

        return context.applicationContext
    }

}