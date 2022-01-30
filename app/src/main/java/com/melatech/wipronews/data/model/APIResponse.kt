package com.melatech.wipronews.data.model

import com.google.gson.annotations.SerializedName

//This data class is populated by the json data from the network api
data class APIResponse(
    @SerializedName("articles")
    val articles: MutableList<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)