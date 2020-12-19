package com.nytimes.news.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nytimes.news.presentation.adapter.NewsListAdapter
import com.nytimes.news.presentation.interfaces.BaseView
import com.nytimes.news.presentation.viewmodel.NewsListViewModel
import com.nytimes.news.utils.NetworkUtil
import com.nytimes.news.utils.Status
import com.google.android.material.snackbar.Snackbar
import com.nytimes.news.R
import com.nytimes.news.databinding.FragmentNewsListBinding

class NewsListFragment : Fragment(),
    BaseView {

    private lateinit var newsListViewModel: NewsListViewModel
    private lateinit var dataBinding: FragmentNewsListBinding
    private lateinit var dataListAdapter: NewsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_news_list, container, false)
        dataBinding.lifecycleOwner = viewLifecycleOwner
        setupViews()
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
        observeViewModel()
    }

    /*Bind the data to the UI*/
    override fun setupViews() {
        dataBinding.recyclerViewPopularNews.setHasFixedSize(true)
        dataListAdapter = NewsListAdapter(context)
    }

    override fun observeViewModel() {
        if (NetworkUtil.isNetworkConnected(requireContext())) {
            newsListViewModel.getNews().observe(
                viewLifecycleOwner,
                Observer {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.LOADING -> {
                                showProgressBar()
                            }

                            Status.SUCCESS -> {
                                hideProgressBar()
                                dataBinding.recyclerViewPopularNews.adapter = dataListAdapter
                                dataListAdapter.setResults(it.data?.results)
                            }

                            Status.ERROR -> {
                                hideProgressBar()
                                showSnackBar(it.message)
                            }
                        }
                    }
                }
            )
        } else {
            showDialog()
        }
    }

    /*Set the ViewModel and Observe the live data*/
    override fun setupViewModel() {
        newsListViewModel = ViewModelProvider(this).get(NewsListViewModel::class.java)
        dataBinding.newsListViewModel = newsListViewModel
    }

    override fun showProgressBar() {
        dataBinding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        dataBinding.progressBar.visibility = View.INVISIBLE
    }

    override fun showSnackBar(info: String?) {
        Snackbar.make(
            dataBinding.constraintLayoutList,
            info.toString(),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun showDialog() {
        AlertDialog.Builder(requireContext()).setTitle("No Internet Connection")
            .setMessage("Please check your internet connection and try again")
            .setPositiveButton(android.R.string.ok) { _, _ -> }
            .setIcon(android.R.drawable.ic_dialog_alert).show()
    }
}
