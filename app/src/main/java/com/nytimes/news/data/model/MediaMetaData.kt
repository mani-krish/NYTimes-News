package com.nytimes.news.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MediaMetaData(
    @SerializedName("url") val url: String,
    @SerializedName("format") val format: String,
    @SerializedName("height") val height: Int,
    @SerializedName("width") val width: Int
) : Serializable
