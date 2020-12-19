package com.nytimes.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nytimes.news.MockitoUtils.mock
import com.nytimes.news.data.model.Results
import com.nytimes.news.presentation.viewmodel.NewsDetailViewModel
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class NewsDetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var newsDetailViewModel: NewsDetailViewModel

    private val observer: Observer<Results> = mock()

    @Before
    fun before() {
        newsDetailViewModel =
            NewsDetailViewModel()
        newsDetailViewModel.getSelectedNews().observeForever(observer)
    }

    @Test
    fun test_setSelectedNews() {
        val expectedResult = Results(
            uri = "nyt://article/3de96929-a13e-5349-8a20-862a15f6867f",
            url = "https://www.nytimes.com/2020/12/16/health/covid-pfizer-vaccine-allergic-reaction.html",
            id = 100,
            assetId = 100,
            source = "New York Times",
            publishedDate = "2020-12-16",
            updated = "2020-12-17 10:28:30",
            section = "Health",
            subsection = "",
            nytdsection = "health",
            adxAdxKeywordswords = "",
            column = "",
            byline = "",
            type = "Article",
            title = "2 Alaska Health Workers Got Emergency Treatment After Receiving Pfizer’s Vaccine",
            abstract = "",
            desFacet = emptyList(),
            orgFacet = emptyList(),
            perFacet = emptyList(),
            geoFacet = emptyList(),
            media = emptyList(),
            etaId = 1
        )
        newsDetailViewModel.setSelectNews(expectedResult)
    }

    @Test
    fun test_getSelectedNews() {
        val expectedResult = Results(
            uri = "nyt://article/3de96929-a13e-5349-8a20-862a15f6867f",
            url = "https://www.nytimes.com/2020/12/16/health/covid-pfizer-vaccine-allergic-reaction.html",
            id = 100,
            assetId = 100,
            source = "New York Times",
            publishedDate = "2020-12-16",
            updated = "2020-12-17 10:28:30",
            section = "Health",
            subsection = "",
            nytdsection = "health",
            adxAdxKeywordswords = "",
            column = "",
            byline = "",
            type = "Article",
            title = "2 Alaska Health Workers Got Emergency Treatment After Receiving Pfizer’s Vaccine",
            abstract = "",
            desFacet = emptyList(),
            orgFacet = emptyList(),
            perFacet = emptyList(),
            geoFacet = emptyList(),
            media = emptyList(),
            etaId = 1
        )
        newsDetailViewModel.setSelectNews(expectedResult)

        assertEquals(newsDetailViewModel.getSelectedNews().value, expectedResult)

        val captor = ArgumentCaptor.forClass(Results::class.java)
        captor.run {
            verify(observer, times(1)).onChanged(capture())
            assertEquals(expectedResult, value)
        }
    }
}
