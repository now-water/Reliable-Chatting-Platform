package com.example.chattingapp.service.util.rest

import io.reactivex.functions.Consumer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Logger
import kotlin.math.log

class RestApiServiceCallback<T : Any?>(val callbackOnResponse: Consumer<T>, val failureCallback : Runnable? = null) : Callback<T> {
    private val logger = Logger.getLogger(RestApiServiceCallback::class.java.name)

    override fun onResponse(call: Call<T>, response: Response<T>){
        logger.info("[RestApi] : status code is " + response.code().toString())

        if(response.code() != 200 ) {
            failureCallback?.run()
            logger.info(response.message())
            return
        }

        callbackOnResponse.accept(response.body() as T)
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        failureCallback?.run()
        logger.info(t.message)
    }
}