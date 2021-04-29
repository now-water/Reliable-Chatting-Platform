package com.example.chattingapp.service

import com.example.chattingapp.dto.Message
import com.example.chattingapp.service.util.stomp.StompApiService
import com.google.gson.Gson
import com.google.gson.JsonParseException
import io.reactivex.functions.Consumer
import java.lang.Exception
import java.util.logging.Logger

class MessageApiService(val stompApiService: StompApiService) {
    private val logger = Logger.getLogger(MessageApiService.javaClass.name)

    private val sendEndpointPrefix = "/pub/chat/message/"
    private val receiveEndPointPrefix = "/sub/chat/room/"

    fun sendMessage(userId: Int, roomId : Int, message: String){
        stompApiService.send("$sendEndpointPrefix$userId/$roomId", message){
            if(!it) throw IllegalStateException("send chat message status is not 200")
        }
    }

    fun subscribeRoom(roomId: Int, callback: Consumer<Message>){
        stompApiService.subscribe(receiveEndPointPrefix + roomId){
            try {
                logger.info(it);
                val chatMessage = Gson().fromJson(it, Message::class.java)
                callback.accept(chatMessage)
            }catch (e : JsonParseException) {
                throw IllegalStateException("received chat message format is different ")
            }catch (e : Exception){
                e.printStackTrace();
            }
        }
    }

    companion object{
        val instance = MessageApiService(StompApiService())
    }
}