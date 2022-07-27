package com.shubham.tmdbclient.data.model.tv_show


import com.google.gson.annotations.SerializedName

data class TvShowList(

    @SerializedName("results")
    val tvShows: List<TvShow>,

    )