package com.example.chattingapp.service

import com.example.chattingapp.dto.ChatMessage
import com.example.chattingapp.service.util.stomp.StompApiService
import com.google.gson.Gson
import io.reactivex.functions.Consumer
import java.lang.Exception

class ChatMessageApiService(val stompApiService: StompApiService) {
    private val sendEndpointPrefix = "/app/"
    private val brokerPrefix = "/topic/"

    fun sendMessage(roomId : Int, message: ChatMessage){
        stompApiService.send(sendEndpointPrefix + roomId, Gson().toJson(message)){
            if(!it) throw IllegalStateException("send chat message status is not 200")
        }
    }

    fun subscribeRoom(roomId: Int, callback: Consumer<ChatMessage>){
        stompApiService.subscribe(brokerPrefix + roomId){
            try {
                val chatMessage = Gson().fromJson(it, ChatMessage::class.java)
                callback.accept(chatMessage)
            }catch (e : Exception){
                throw IllegalStateException("received chat message format is different ")
            }
        }
    }

    companion object{
        val instance = ChatMessageApiService(StompApiService())
    }
}