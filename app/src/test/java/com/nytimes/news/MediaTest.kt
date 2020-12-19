package com.nytimes.news

import com.nytimes.news.data.model.Media
import com.nytimes.news.data.model.MediaMetaData
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MediaTest {

    private lateinit var media: Media

    @Before
    fun setUp() {
        media = Media(
            type = "image",
            subtype = "photo",
            caption = "Vaccines being prepared in Fargo, N.D., on Monday",
            copyright = "Tim Gruber for The New York Times",
            approvedForSyndication = 1,
            mediaMetaData = emptyList()
        )
    }

    @Test
    fun testType() {
        Assert.assertEquals(media.type, "image")
    }

    @Test
    fun testSubType() {
        Assert.assertEquals(media.subtype, "photo")
    }

    @Test
    fun testCaption() {
        Assert.assertEquals(media.caption, "Vaccines being prepared in Fargo, N.D., on Monday")
    }

    @Test
    fun testCopyRight() {
        Assert.assertEquals(media.copyright, "Tim Gruber for The New York Times")
    }

    @Test
    fun approvedForSyndicationTest() {
        Assert.assertEquals(media.approvedForSyndication, 1)
    }

    @Test
    fun testMediaMetaData() {
        Assert.assertEquals(media.mediaMetaData, emptyList<MediaMetaData>())
    }
}
