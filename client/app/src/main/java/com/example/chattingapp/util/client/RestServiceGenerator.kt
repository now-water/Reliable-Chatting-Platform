package com.example.chattingapp.util.client

import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.logging.Logger


object RestServiceGenerator {
    private val logger = Logger.getLogger(RestServiceGenerator.javaClass.name)

    private val EMULATOR_URL = "http://10.0.2.2:8080/"

    fun <S> createService(serviceClass: Class<S>): S {
        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)
        val myCookieJar = JavaNetCookieJar(cookieManager)

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .cookieJar(myCookieJar)
            .retryOnConnectionFailure(true)
            .build()

        val builder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(EMULATOR_URL)
            .client(okHttpClient)
        val retrofit = builder.build()
        return retrofit.create(serviceClass)
    }

    private class SessionCookieJar : CookieJar {
        private lateinit var cookies : List<Cookie>

        override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
            if (url.encodedPath().endsWith("login")) {
                this.cookies = cookies
            }
        }

        override fun loadForRequest(url: HttpUrl): List<Cookie> {
            if (!url.encodedPath().endsWith("login") && cookies != null) {
                return cookies;
            }
            return Collections.emptyList();
        }

    }
}