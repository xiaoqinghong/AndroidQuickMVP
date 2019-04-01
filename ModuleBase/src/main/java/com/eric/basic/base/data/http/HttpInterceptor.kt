package com.eric.basic.base.data.http

import android.util.Log
import com.eric.basic.base.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HttpInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        if (BuildConfig.DEBUG) {
            Log.d("request", request.url().toString())
            response.body()?.apply {
                Log.d("response", this.string())
            }
        }
        return response
    }
}