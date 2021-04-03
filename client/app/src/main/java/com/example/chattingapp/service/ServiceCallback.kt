package com.example.chattingapp.service

import io.reactivex.functions.Consumer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServiceCallback<T : Any?>(val callbackOnResponse : Consumer<Response<T>>) : Callback<T> {
    override fun onResponse(call: Call<T>, response: Response<T>) {
        callbackOnResponse.accept(response)
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        t.printStackTrace()
    }
}