package com.nytimes.news.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nytimes.news.data.model.Results
import com.nytimes.news.presentation.interfaces.BaseView
import com.nytimes.news.presentation.viewmodel.NewsDetailViewModel
import com.google.android.material.snackbar.Snackbar
import com.nytimes.news.R
import com.nytimes.news.databinding.ActivityNewsDetailBinding

class NewsDetailActivity :
    AppCompatActivity(),
    BaseView {

    private lateinit var newsItemViewModel: NewsDetailViewModel
    private lateinit var dataBindingDetail: ActivityNewsDetailBinding
    private lateinit var context: Context
    private lateinit var result: Results

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBindingDetail = DataBindingUtil.setContentView(this, R.layout.activity_news_detail)
        dataBindingDetail.lifecycleOwner = this
        context = this
        result = intent.getSerializableExtra("Result") as Results
        setupViews()
        setupViewModel()
        observeViewModel()
    }

    override fun observeViewModel() {
        hideProgressBar()
        newsItemViewModel.getSelectedNews().observe(
            this,
            Observer {
                dataBindingDetail.tvNewsTitle.text = it.title
                dataBindingDetail.tvNewsAbstraction.text = it.abstract
                if (it.media.isNotEmpty()) {
                    dataBindingDetail.tvNewsContent.text = it.media[0].caption
                }
                dataBindingDetail.tvNewsWriter.text = it.byline
                dataBindingDetail.tvPublishedTime.text = it.updated
                dataBindingDetail.toolbarDetail.title = it.section
            }
        )
    }

    /*Bind the data to the UI*/
    override fun setupViews() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        showProgressBar()
        dataBindingDetail.toolbarDetail.setNavigationOnClickListener { onBackPressed() }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    /*Set the ViewModel and Observe the live data*/
    override fun setupViewModel() {
        newsItemViewModel = ViewModelProvider(this).get(NewsDetailViewModel::class.java)
        newsItemViewModel.setSelectNews(result)
        dataBindingDetail.newsDetailViewModel = newsItemViewModel
    }

    override fun showProgressBar() {
        dataBindingDetail.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        dataBindingDetail.progressBar.visibility = View.INVISIBLE
    }

    override fun showSnackBar(info: String?) {
        Snackbar.make(
            dataBindingDetail.constraintLayoutDetail,
            context.getString(R.string.no_internet_connection),
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
