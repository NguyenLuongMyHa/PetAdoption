package com.myha.petadoption.data.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class Connectivity(private val context: Context) : Interceptor {

    //TODO check version, permission api 21
    @RequiresApi(Build.VERSION_CODES.M)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable(context)) throw NoInternetException("Please Check Your Connectivity")
        return chain.proceed(chain.request())
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val capabilities =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                Timber.i("NetworkCapabilities.TRANSPORT_CELLULAR")
                true
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                Timber.i("NetworkCapabilities.TRANSPORT_WIFI")
                true
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                Timber.i("NetworkCapabilities.TRANSPORT_ETHERNET")
                true
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> {
                Timber.i("NetworkCapabilities.TRANSPORT_VPN")
                true
            }
            else -> false
        }
    }
}