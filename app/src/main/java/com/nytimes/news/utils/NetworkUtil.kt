package com.nytimes.news.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetworkUtil {

    companion object {

        var API_BASE_URL: String = "https://api.nytimes.com/svc/mostpopular/v2/"
        var API_KEY = "AeoBGpLmUNsR25Bgv6bsMA5iVQLIO691"

        /**
         * Checks for internet connection
         *
         * @param context Context
         * @return True if internet is available, else false.
         */
        fun isNetworkConnected(context: Context): Boolean {
            val connMgr =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
            return networkInfo?.isConnected == true
        }
    }
}
