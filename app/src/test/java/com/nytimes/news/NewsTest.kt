package com.nytimes.news

import com.nytimes.news.data.model.News
import com.nytimes.news.data.model.Results
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class NewsTest {
    private lateinit var news: News

    @Before
    fun setUp() {
        news = News(
            status = "OK",
            copyright = "Copyright (c) 2020 The New York Times Company.  All Rights Reserved.",
            numResults = 20,
            results = emptyList()
        )
    }

    @Test
    fun typeTest() {
        Assert.assertEquals(news.status, "OK")
    }

    @Test
    fun subTypeTest() {
        Assert.assertEquals(
            news.copyright,
            "Copyright (c) 2020 The New York Times Company.  All Rights Reserved."
        )
    }

    @Test
    fun captionTest() {
        Assert.assertEquals(news.numResults, 20)
    }

    @Test
    fun resultsTest() {
        Assert.assertEquals(news.results, emptyList<Results>())
    }
}
