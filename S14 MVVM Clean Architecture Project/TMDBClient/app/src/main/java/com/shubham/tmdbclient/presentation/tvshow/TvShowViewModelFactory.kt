package com.shubham.tmdbclient.presentation.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shubham.tmdbclient.domain.use_cases.GetTvShowsUseCase
import com.shubham.tmdbclient.domain.use_cases.UpdateTvShowUseCase

class TvShowViewModelFactory(
    private val getTvShowsUseCase: GetTvShowsUseCase,
    private val updateTvShowUseCase: UpdateTvShowUseCase
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return TvShowViewModel(getTvShowsUseCase, updateTvShowUseCase) as T
    }


}