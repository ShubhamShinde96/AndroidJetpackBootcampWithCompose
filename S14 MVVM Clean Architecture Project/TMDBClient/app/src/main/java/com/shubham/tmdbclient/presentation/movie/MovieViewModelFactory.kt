package com.shubham.tmdbclient.presentation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shubham.tmdbclient.domain.use_cases.GetMoviesUseCase
import com.shubham.tmdbclient.domain.use_cases.UpdateMoviesUseCase

class MovieViewModelFactory(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val updateMoviesUseCase: UpdateMoviesUseCase
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Here we need to construct a movieViewModel passing useCase instances as arguments & return as T

        return MovieViewModel(getMoviesUseCase, updateMoviesUseCase) as T
    }


}