package com.example.recipe.common.network

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class DefaultQueryParameterInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestOriginal: Request = chain.request()
        val url: HttpUrl = requestOriginal.url
            .newBuilder()
            .build()
        val request = requestOriginal.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}