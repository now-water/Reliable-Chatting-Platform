package com.example.chattingapp.service.util.rest

import io.reactivex.functions.Consumer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Logger

class RestApiServiceCallback<T : Any?>(val callbackOnResponse: Consumer<T>) : Callback<T> {
    private val logger = Logger.getLogger(RestApiServiceCallback::class.java.name)

    override fun onResponse(call: Call<T>, response: Response<T>){
        if(response.code() != 200)
            throw IllegalStateException("status code is not 200")

        logger.info("status code is " + response.code().toString())
        logger.info(response.body().toString())
        callbackOnResponse.accept(response.body() as T)
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        // printstack 대신에 Exception
        throw IllegalStateException(t)
    }
}