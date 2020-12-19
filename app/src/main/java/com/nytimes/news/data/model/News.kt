package com.nytimes.news.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class News(
    @SerializedName("status") val status: String,
    @SerializedName("copyright") val copyright: String,
    @SerializedName("num_results") val numResults: Int,
    @SerializedName("results") val results: List<Results>
) : Serializable
