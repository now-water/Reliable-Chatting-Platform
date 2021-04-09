package com.example.chattingapp.service

import android.util.Log
import android.util.Log.INFO
import com.example.chattingapp.dto.ChatMessage
import com.example.chattingapp.service.util.rest.RestApiServiceGenerator
import com.example.chattingapp.service.util.stomp.StompApiService
import com.google.gson.Gson
import com.google.gson.JsonParseException
import io.reactivex.functions.Consumer
import java.lang.Exception
import java.util.logging.Level.INFO
import java.util.logging.Logger

class ChatMessageApiService(val stompApiService: StompApiService) {
    private val logger = Logger.getLogger(ChatMessageApiService.javaClass.name)

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
                logger.info(it);
                val chatMessage = Gson().fromJson(it, ChatMessage::class.java)
                callback.accept(chatMessage)
            }catch (e : JsonParseException) {
                throw IllegalStateException("received chat message format is different ")
            }catch (e : Exception){
                e.printStackTrace();
            }
        }
    }

    companion object{
        val instance = ChatMessageApiService(StompApiService())
    }
}