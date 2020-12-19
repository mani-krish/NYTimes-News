package com.nytimes.news.presentation.viewmodel

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.nytimes.news.data.model.Media
import com.nytimes.news.data.repository.NewsRepository
import com.nytimes.news.utils.Resource
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers

class NewsListViewModel : ViewModel() {

    fun getNews() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val repository =
                NewsRepository()
            val responseData = repository.getNewsData()
            if (responseData.isSuccessful) {
                emit(Resource.success(data = responseData.body()))
            } else {
                emit(Resource.error(data = null, message = responseData.message()))
            }
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message.toString()))
        }
    }

    companion object {
        @JvmStatic
        @BindingAdapter("image", "placeholder")
        fun loadImage(view: ImageView, media: List<Media>?, placeHolder: Drawable) {
            if (media != null && media.isNotEmpty() && media[0].mediaMetaData.isNotEmpty()) {
                Glide.with(view.context)
                    .load(media[0].mediaMetaData[0].url)
                    .circleCrop()
                    .into(view)
            } else {
                view.setImageDrawable(placeHolder)
            }
        }
    }
}
