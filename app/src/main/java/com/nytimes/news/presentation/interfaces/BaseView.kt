package com.nytimes.news.presentation.interfaces

interface BaseView {
    fun showProgressBar()

    fun hideProgressBar()

    fun showSnackBar(info: String?)

    fun setupViews()

    fun setupViewModel()

    fun observeViewModel()
}
