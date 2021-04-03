package com.example.chattingapp.util.client

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.Logger


object RestServiceGenerator {
    private val logger = Logger.getLogger(RestServiceGenerator.javaClass.name)

    private val EMULATOR_URL = "http://10.0.2.2:8080/"

    fun <S> createService(serviceClass: Class<S>): S {
        val builder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(EMULATOR_URL)
            .client(OkHttpClient())
        val retrofit = builder.build()
        return retrofit.create(serviceClass)
    }
}