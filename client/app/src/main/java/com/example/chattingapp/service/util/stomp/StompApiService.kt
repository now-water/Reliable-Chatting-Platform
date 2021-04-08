package com.example.chattingapp.service.util.stomp

import android.annotation.SuppressLint
import com.gmail.bishoybasily.stomp.lib.StompClient
import io.reactivex.functions.Consumer
import okhttp3.OkHttpClient
import java.lang.IllegalStateException
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.logging.Logger

class StompApiService {
    private val logger = Logger.getLogger(StompApiService::class.java.name)

    private val EMULATOR_URL = "ws://10.0.2.2:8080/"
    private val END_POINT = "endpoint/websocket"

    private val INTERVAL_MILLIS = 5000L
    private val TIME_OUT_SECONDS = 10L

    private lateinit var stomp:StompClient

    init {
        connect()
    }

    @SuppressLint("CheckResult")
    fun connect() {
        val client = OkHttpClient.Builder()
            .readTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .build()

        stomp = StompClient(client, INTERVAL_MILLIS).apply { this@apply.url = EMULATOR_URL + END_POINT }
        println(stomp.url)
        stomp.connect().subscribe(){
            throw IllegalStateException("cannot connect to server with stomp protocol")
            logger.info(it.type.toString())
        }
    }

    @SuppressLint("CheckResult")
    fun send(topic:String, message: String, callback : Consumer<Boolean>){
        stomp.send(topic, message).subscribe(){
            callback.accept(it)
        }
    }

    @SuppressLint("CheckResult")
    fun subscribe(topic: String, callback: Consumer<String>){
        stomp.join(topic).subscribe(){
            callback.accept(it)
        }
    }
}