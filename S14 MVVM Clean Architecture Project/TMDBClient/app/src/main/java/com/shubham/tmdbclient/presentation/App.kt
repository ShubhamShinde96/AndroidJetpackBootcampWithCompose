package com.shubham.tmdbclient.presentation

import android.app.Application
import com.shubham.tmdbclient.BuildConfig
import com.shubham.tmdbclient.presentation.di.Injector
import com.shubham.tmdbclient.presentation.di.artist.ArtistSubComponent
import com.shubham.tmdbclient.presentation.di.core.*
import com.shubham.tmdbclient.presentation.di.movie.MovieSubComponent
import com.shubham.tmdbclient.presentation.di.tvshow.TvShowSubComponent

class App: Application(), Injector {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext)) // using these modules here to pass the constructor parameters to them
            .netModule(NetModule(BuildConfig.BASE_URL)) // using these modules here to pass the constructor parameters to them
            .remoteDataModule(RemoteDataModule(BuildConfig.API_KEY)) // using these modules here to pass the constructor parameters to them
            .build()

        // We don't need to add all the core modules to the builder, we only need to add modules with constructor
        // parameters.

    }

    override fun createMovieSubComponent(): MovieSubComponent {

        return appComponent.movieSubComponent().create()
    }

    override fun createTvShowSubComponent(): TvShowSubComponent {

        return appComponent.tvShowSubComponent().create()
    }

    override fun createArtistSubComponent(): ArtistSubComponent {

        return appComponent.artistSubComponent().create()
    }
}