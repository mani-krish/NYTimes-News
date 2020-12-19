package com.nytimes.news

import org.mockito.Mockito

object MockitoUtils {
    inline fun <reified T> mock(): T = Mockito.mock(T::class.java)
}
