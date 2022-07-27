package com.shubham.newsapiclientproject.data.model

import com.google.gson.annotations.SerializedName

data class API_Response(

    @SerializedName("articles")
    val articles: List<Article>,

    @SerializedName("status")
    val status: String,

    @SerializedName("totalResults")
    val totalResults: Int
)