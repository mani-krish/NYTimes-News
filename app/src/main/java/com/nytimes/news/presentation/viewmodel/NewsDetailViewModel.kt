package com.nytimes.news.presentation.viewmodel

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nytimes.news.data.model.Media
import com.nytimes.news.data.model.Results
import com.bumptech.glide.Glide

class NewsDetailViewModel : ViewModel() {

    private val newsItemMutableLiveData = MutableLiveData<Results>()

    fun setSelectNews(result: Results) {
        newsItemMutableLiveData.value = result
    }

    fun getSelectedNews(): MutableLiveData<Results> {
        return newsItemMutableLiveData
    }

    companion object {
        @JvmStatic
        @BindingAdapter("imageDetail", "placeholder")
        fun loadImage(view: ImageView, media: List<Media>?, placeHolder: Drawable) {
            if (media != null && media.isNotEmpty() && media[0].mediaMetaData.isNotEmpty()) {
                Glide.with(view.context)
                    .load(media[0].mediaMetaData[0].url)
                    .into(view)
            } else {
                view.setImageDrawable(placeHolder)
            }
        }

        @JvmStatic
        @BindingAdapter("newsContent")
        fun newsContent(view: TextView, media: List<Media>?) {
            if (media != null && media.isNotEmpty()) {
                view.text = media[0].caption
            } else {
                view.text = ""
            }
        }
    }
}
