package com.snj07.rassignment.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class InternetConnectivity {

    companion object {
        var mApplicationContext: Context? = null
        fun init(context: Context): InternetConnectivity {
            mApplicationContext = context
            return InternetConnectivity()
        }

        fun getNetworkInfo(): NetworkInfo? {
            val cm = mApplicationContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm?.activeNetworkInfo
        }

        fun isConnected(): Boolean {
            val info = InternetConnectivity.getNetworkInfo()
            return info != null && info.isConnected
        }

    }
}