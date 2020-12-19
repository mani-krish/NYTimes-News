package com.nytimes.news

import com.nytimes.news.data.model.Results
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ResultsTest {
    private lateinit var results: Results

    @Before
    fun setUp() {
        results = Results(
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
    }

    @Test
    fun testUri() {
        Assert.assertEquals(results.uri, "nyt://article/3de96929-a13e-5349-8a20-862a15f6867f")
    }

    @Test
    fun testUrl() {
        Assert.assertEquals(
            results.url,
            "https://www.nytimes.com/2020/12/16/health/covid-pfizer-vaccine-allergic-reaction.html"
        )
    }

    @Test
    fun testId() {
        Assert.assertEquals(results.id, 100)
    }

    @Test
    fun testAssetId() {
        Assert.assertEquals(results.assetId, 100)
    }

    @Test
    fun testSource() {
        Assert.assertEquals(results.source, "New York Times")
    }

    @Test
    fun testUpdatedDate() {
        Assert.assertEquals(results.updated, "2020-12-17 10:28:30")
    }

    @Test
    fun testPublishedDate() {
        Assert.assertEquals(results.publishedDate, "2020-12-16")
    }

    @Test
    fun testSection() {
        Assert.assertEquals(results.section, "Health")
    }
}
