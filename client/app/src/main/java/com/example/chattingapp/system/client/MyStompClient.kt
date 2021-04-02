package com.example.chattingapp.system.client

import com.gmail.bishoybasily.stomp.lib.StompClient
import okhttp3.OkHttpClient
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.logging.Logger

object MyStompClient {
    private val logger = Logger.getLogger(MyStompClient.javaClass.name)

    private val EMULATOR_URL = "ws://10.0.2.2:8080"
    private val END_POINT = "/endpoint/websocket"

    private val INTERVAL_MILLIS = 5000L
    private val TIME_OUT_SECONDS = 10L

    private val messageBuffer = MessageBuffer<String>()
    private lateinit var stomp:StompClient

    fun connect() {
        val client = OkHttpClient.Builder()
            .readTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .build()

        stomp = StompClient(client, INTERVAL_MILLIS).apply { this@apply.url = EMULATOR_URL + END_POINT }
        println(stomp.url)
        stomp.connect().subscribe(){
            logger.info(it.type.toString())
        }
    }

    fun send(topic:String, message: String){
        stomp.send(topic, message).subscribe(){
            logger.info("send status : " + it)
        }
    }

    fun subscribe(topic: String){
        stomp.join(topic).subscribe(){
            it -> {
                messageBuffer.addMessage(topic, it)
                logger.info("received : " + it)
                println(it)
            }
        }
    }

    fun getData(topic: String):List<String>{
        return messageBuffer.getMessages(topic)
    }
}