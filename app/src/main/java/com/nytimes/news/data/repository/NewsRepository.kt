package com.nytimes.news.data.repository

import com.nytimes.news.data.source.ApiClient
import com.nytimes.news.data.source.ApiService
import com.nytimes.news.utils.NetworkUtil

class NewsRepository {
    private var service: ApiService = ApiClient.apiService
    suspend fun getNewsData() = service.fetchNews(NetworkUtil.API_KEY)
}

