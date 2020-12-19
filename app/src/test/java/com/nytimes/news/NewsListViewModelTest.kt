package com.nytimes.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nytimes.news.data.model.Media
import com.nytimes.news.data.model.MediaMetaData
import com.nytimes.news.data.model.News
import com.nytimes.news.data.model.Results
import com.nytimes.news.data.source.ApiClient
import com.nytimes.news.data.source.ApiService
import com.nytimes.news.presentation.viewmodel.NewsListViewModel
import com.nytimes.news.utils.NetworkUtil
import com.nytimes.news.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.Response

class NewsListViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var apiUsersObserver: Observer<Resource<out News?>>

    private lateinit var newsListViewModel: NewsListViewModel

    private val loadingResource = Resource.loading(null)

    private val successResource = Resource.success(null)

    private val errorResource = Resource.error(null, "Error")

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mock(NetworkUtil::class.java)
        mock(ApiClient::class.java)
        mock(ApiService::class.java)
        mock(Response::class.java)
        mock(News::class.java)
        mock(Results::class.java)
        mock(MediaMetaData::class.java)
        mock(MutableLiveData::class.java)
        mock(LiveData::class.java)
        mock(Media::class.java)
        apiUsersObserver = MockitoUtils.mock()
        newsListViewModel = NewsListViewModel()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_get_news_return_loading() {

        testCoroutineRule.runBlockingTest {

            newsListViewModel.getNews().observeForever(apiUsersObserver)

            newsListViewModel.getNews()

            delay(1000)

            verify(apiUsersObserver, timeout(50)).onChanged(Resource.loading(null))
            verify(apiUsersObserver, timeout(50)).onChanged(loadingResource)

            newsListViewModel.getNews().removeObserver(apiUsersObserver)
        }
    }

}
