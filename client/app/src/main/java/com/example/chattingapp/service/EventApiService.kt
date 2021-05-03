package com.example.chattingapp.service

import com.example.chattingapp.dto.EventInvite
import com.example.chattingapp.service.util.stomp.MyStompClient
import com.google.gson.Gson
import io.reactivex.functions.Consumer
import java.util.logging.Logger

class EventApiService() {
    private val logger = Logger.getLogger(MessageApiService.javaClass.name)

    private val invitePrefix = "/pub/event/sub/"
    private val myEventPrefix = "/sub/"

     private var myStompService : MyStompClient = MyStompClient.createInstance()

    fun makeInviteEvent(myId: String, toId:String, eventInvite: EventInvite){
        myStompService.send(invitePrefix + myId + "/" + toId, Gson().toJson(eventInvite)){
            if(!it) throw IllegalStateException("send chat message status is not 200")
        }
    }

    fun subscribeToMyEvent(myId: Int, callback: Consumer<EventInvite>){
        myStompService.subscribe(myEventPrefix + myId, EventInvite::class.java){
            callback.accept(it)
        }
    }

    fun unSubscribeToMyEvent(myId: Int){
        myStompService.disposeTopic(myEventPrefix + myId)
    }

    fun deSubscribeAll(){
        myStompService.disposeTopicAll()
    }

    companion object{
        fun getNewInstance() : EventApiService{
            return EventApiService()
        }
    }
}