package com.shubham.tmdbclient.presentation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.shubham.tmdbclient.domain.use_cases.GetMoviesUseCase
import com.shubham.tmdbclient.domain.use_cases.UpdateMoviesUseCase

class MovieViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val updateMoviesUseCase: UpdateMoviesUseCase
) : ViewModel() {

    fun getMovies() = liveData {

        // since we're using coroutines we'll use liveData block here.
        // We have not explicitly provided any dispatcher here, so coroutine will use the main thread.
        // Since we have used I/O thread in the data sources calling them from the UI thread is the best practice.
        val movieList = getMoviesUseCase.execute()
        emit(movieList)
    }

    fun updateMovieList() = liveData {

        val movieList = updateMoviesUseCase.execute()
        emit(movieList)
    }


}