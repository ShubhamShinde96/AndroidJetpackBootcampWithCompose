package com.shubham.tmdbclient.domain.use_cases

import com.shubham.tmdbclient.data.model.tv_show.TvShow
import com.shubham.tmdbclient.domain.repository.TvShowRepository

class UpdateTvShowUseCase(private val tvShowRepository: TvShowRepository) {

    suspend fun execute(): List<TvShow>? = tvShowRepository.updateTvShows()

}