package com.example.chattingapp.service

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.chattingapp.dto.Message
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MessageApiTest {
    @Test
    fun sendTest(){
        chatMessageApiService.sendMessage(1, chatMessage)
    }

    @Test
    fun receiveTest(){
        chatMessageApiService.subscribeRoom(1){
            assertEquals("myaho", it.content)
        }
        chatMessageApiService.sendMessage(1, chatMessage)
    }

    companion object {
        val chatMessageApiService = MessageApiService.instance
        val chatMessage = Message(1, 1, 0, "muyaho~", 1, "muyaho-아저씨")
    }
}