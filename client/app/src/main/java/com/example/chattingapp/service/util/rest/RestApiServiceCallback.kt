package com.example.chattingapp.service.util.rest

import io.reactivex.functions.Consumer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Logger

class RestApiServiceCallback<T : Any?>(val callbackOnResponse: Consumer<T>, val failureCallback : Runnable? = null) : Callback<T> {
    private val logger = Logger.getLogger(RestApiServiceCallback::class.java.name)

    override fun onResponse(call: Call<T>, response: Response<T>){
        if(response.code() != 200 ) {
            failureCallback?.run()
            return
        }

        logger.info("[RestApi] : status code is " + response.code().toString())
        callbackOnResponse.accept(response.body() as T)
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        failureCallback?.run()
    }
}