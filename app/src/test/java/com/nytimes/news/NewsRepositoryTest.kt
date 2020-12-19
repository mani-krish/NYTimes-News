package com.nytimes.news

import com.nytimes.news.data.model.Media
import com.nytimes.news.data.model.MediaMetaData
import com.nytimes.news.data.model.News
import com.nytimes.news.data.model.Results
import com.nytimes.news.data.repository.NewsRepository
import com.nytimes.news.data.source.ApiClient
import com.nytimes.news.data.source.ApiService
import com.nytimes.news.utils.NetworkUtil
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Response

class NewsRepositoryTest {

    @Before
    fun setUp() {
        Mockito.mock(NetworkUtil::class.java)
        Mockito.mock(ApiClient::class.java)
        Mockito.mock(ApiService::class.java)
        Mockito.mock(Response::class.java)
        Mockito.mock(News::class.java)
        Mockito.mock(Results::class.java)
        Mockito.mock(MediaMetaData::class.java)
        Mockito.mock(Media::class.java)
    }

    @Test
    fun test_repository_get_news_success() = runBlocking {
        val repository = NewsRepository()
        val actualResult = repository.getNewsData()
        assertEquals(true, actualResult.isSuccessful)
    }

    @Test
    fun test_repository_get_news_error() = runBlocking {
        NetworkUtil.API_KEY = "cdnscsdhbdscmn"
        val repository = NewsRepository()
        val actualResult = repository.getNewsData().code()
        val expectedResult = 401
        assertEquals(expectedResult, actualResult)
    }

}