package com.example.chattingapp.service.util.stomp

import android.annotation.SuppressLint
import com.gmail.bishoybasily.stomp.lib.Event
import com.gmail.bishoybasily.stomp.lib.StompClient
import com.google.gson.Gson
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import okhttp3.OkHttpClient
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.logging.Logger

class MyStompClient {
    private val logger = Logger.getLogger(MyStompClient::class.java.name)

    private val LOCAL_URL = "ws://10.0.2.2:8080/"

    private val EC2_URL = "ws://ec2-15-164-94-45.ap-northeast-2.compute.amazonaws.com:3000/"
    private val END_POINT = "ws/websocket"

    private val INTERVAL_MILLIS = 5000L
    private val TIME_OUT_SECONDS = 10L

    private lateinit var topicTable : Hashtable<String, Disposable>
    private lateinit var stomp:StompClient

    private val gson = Gson()

    init {
        stomp = createStompClient()
        connect(stomp)
        createTopicTable()
    }

    private fun createTopicTable(){
        topicTable = Hashtable<String, Disposable>()
    }

    private fun createStompClient() : StompClient{
        val client = OkHttpClient.Builder()
            .readTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .build()

        return StompClient(client, INTERVAL_MILLIS).apply { this@apply.url = EC2_URL + END_POINT }
    }

    @SuppressLint("CheckResult")
    private fun connect(stompClient : StompClient) {
        stomp.connect().subscribe({
            if(it.type != Event.Type.OPENED){
                logger.info("cannot connect via stomp client")
            }
        },{
            logger.info("stomp connect error : ${it.message}")
        })
    }

    @SuppressLint("CheckResult")
    fun send(topic:String, message: String, callback : Consumer<Boolean>){
        stomp.send(topic, message).subscribe({
            callback.accept(it)
        },{
            logger.info("stomp send error : ${it.message}")
        })
    }

    @SuppressLint("CheckResult")
    fun <T : Any?> subscribe(topic: String, clazz:Class<T>, callback: Consumer<T>){
        val topicDispose = stomp.join(topic).subscribe({
            val ret = gson.fromJson(it, clazz)
            callback.accept(ret)
        },{
            logger.info("stomp subscribe error : ${it.message}")
        })
    }

    @SuppressLint("CheckResult")
    fun disposeTopic(topic:String){
        if(topicTable.containsKey(topic)){
            topicTable.remove(topic)?.dispose()
        }
    }

    fun disposeTopicAll(){
        for(topic in topicTable.keys()){
           topicTable.remove(topic)?.dispose()
        }
    }


    companion object {
        val instance = MyStompClient()

        fun createInstance() : MyStompClient {
            return MyStompClient()
        }
    }
}