package com.shubham.tmdbclient.presentation.di.artist

import javax.inject.Scope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME) // need to check about this runtime, it think this annotation indicated to run until the lifecycle of activity
annotation class ArtistScope

// Now we can use this newly created ArtistScope as the scope of ArtistViewModelFactory dependency