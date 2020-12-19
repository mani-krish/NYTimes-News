package com.nytimes.news.data.source

import com.nytimes.news.data.model.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    /*API for Popular News */
    @GET("viewed/7.json")
    suspend fun fetchNews(@Query("api-key") apiKey: String): Response<News>
}
