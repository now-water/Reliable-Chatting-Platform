package com.example.chattingapp.util.client

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.chattingapp.service.util.stomp.MyStompClient
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class MyStompClientTest {
    @Test
    fun sendTest(){
        stompClient.send("/app/chat", "muyaho~~"){
            assertTrue(it)
        }
    }

    @Test
    fun receiveTest(){
        stompClient.subscribe("/topic/chat"){
            assertTrue(it.equals("hello muyaho~"))
        }
        TimeUnit.SECONDS.sleep(1)
        stompClient.send("/app/chat", "hello muyaho~"){}
    }

    companion object {
        @JvmStatic
        var stompClient : MyStompClient = MyStompClient

        init{
            stompClient.connect()
        }
    }
}