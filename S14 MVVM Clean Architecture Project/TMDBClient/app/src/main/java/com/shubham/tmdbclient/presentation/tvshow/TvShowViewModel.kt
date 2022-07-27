package com.shubham.tmdbclient.presentation.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.shubham.tmdbclient.domain.use_cases.GetTvShowsUseCase
import com.shubham.tmdbclient.domain.use_cases.UpdateTvShowUseCase

class TvShowViewModel(
    private val getTvShowsUseCase: GetTvShowsUseCase,
    private val updateTvShowUseCase: UpdateTvShowUseCase
): ViewModel() {

    fun getTvShow() = liveData {

        val tvShowsList = getTvShowsUseCase.execute()
        emit(tvShowsList)
    }

    fun updateTvShow() = liveData {

        val tvShowsList = updateTvShowUseCase.execute()
        emit(tvShowsList)
    }

}