package com.nytimes.news.presentation.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nytimes.news.data.model.Results
import com.nytimes.news.presentation.interfaces.UIEvents
import com.nytimes.news.presentation.view.NewsDetailActivity
import com.nytimes.news.R
import com.nytimes.news.databinding.ItemNewsBinding

class NewsListAdapter(private val context: Context?) :
    RecyclerView.Adapter<NewsListAdapter.DataViewHolder>(),
    UIEvents {

    private var listItems = listOf<Results>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val newsItemBinding: ItemNewsBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_news, parent, false)
        return DataViewHolder(newsItemBinding)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(listItems[position])
        holder.itemRowBinding!!.newsItem = listItems[position]
        holder.itemRowBinding!!.itemClickListener = this
    }

    /*Bind the model to the list item*/
    inner class DataViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var itemRowBinding: ItemNewsBinding? = binding
        fun bind(item: Results) {
            with(binding) {
                tvTitleNews.text = item.title
                tvAbstractNews.text = item.abstract
                tvPublishedDate.text = item.publishedDate
            }
        }
    }

    /*Update the results to the list*/
    fun setResults(result: List<Results>?) {
        this.listItems = result!!
        notifyDataSetChanged()
    }

    override fun onItemClick(result: Results) {
        val intent = Intent(context, NewsDetailActivity::class.java)
        intent.putExtra("Result", result)
        context!!.startActivity(intent)
    }
}
