package com.example.chattingapp.system.client

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class MyStompClientTest {

    @Test
    fun sendTest(){
        val stompClient = MyStompClient
        stompClient.connect()
        stompClient.send("/app/chat", "muyaho~~")
    }

    @Test
    fun receiveTest(){
        val stompClient = MyStompClient
        stompClient.connect()
        stompClient.subscribe("/topic/chat")
        TimeUnit.SECONDS.sleep(1)
        stompClient.send("/app/chat", "hello muyaho~")
    }
}