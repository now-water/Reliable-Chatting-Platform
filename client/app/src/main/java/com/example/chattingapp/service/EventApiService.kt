package com.example.chattingapp.service

import com.example.chattingapp.dto.EventInvite
import com.example.chattingapp.service.util.stomp.StompApiService
import com.google.gson.Gson
import com.google.gson.JsonParseException
import io.reactivex.functions.Consumer
import java.lang.Exception
import java.util.logging.Logger

class EventApiService(val stompApiService: StompApiService) {
    private val logger = Logger.getLogger(MessageApiService.javaClass.name)

    private val invitePrefix = "/pub/event/sub/"
    private val myEventPrefix = "/sub/"

    fun makeInviteEvent(myId: String, toId:String, eventInvite: EventInvite){
        stompApiService.send(invitePrefix + myId + "/" + toId, Gson().toJson(eventInvite)){
            if(!it) throw IllegalStateException("send chat message status is not 200")
        }
    }

    fun subscribeToMyEvent(myId: String, callback: Consumer<EventInvite>){
        stompApiService.subscribe(myEventPrefix + myId){
            try {
                logger.info(it);
                val eventMessage = Gson().fromJson(it, EventInvite::class.java)
                callback.accept(eventMessage)
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