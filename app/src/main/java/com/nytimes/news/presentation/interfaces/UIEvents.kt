package com.nytimes.news.presentation.interfaces

import com.nytimes.news.data.model.Results

interface UIEvents {
    fun onItemClick(result: Results)
}
