package com.example.chattingapp.service

import com.example.chattingapp.dto.Message
import com.example.chattingapp.service.util.rest.RestApiService
import com.example.chattingapp.service.util.rest.RestApiServiceCallback
import com.example.chattingapp.service.util.stomp.MyStompClient
import io.reactivex.functions.Consumer
import java.util.logging.Logger

class MessageApiService() {
    private val logger = Logger.getLogger(MessageApiService.javaClass.name)

    private val sendEndpointPrefix = "/pub/chat/message/"
    private val receiveEndPointPrefix = "/sub/chat/room/"

    private var myStompService: MyStompClient = MyStompClient.createInstance()
    private var restApiService : RestApiService = RestApiService.instance

    fun sendMessage(userId: Int, roomId : Int, message: String){
        myStompService.send("$sendEndpointPrefix$userId/$roomId", message){
            if(!it) throw IllegalStateException("send chat message status is not 200")
        }
    }

    fun getAllMessages(roomId: Int, callback: Consumer<List<Message>>){
        restApiService.getMessages(roomId).enqueue(RestApiServiceCallback(callback))
    }

    fun subscribeRoom(roomId: Int, callback: Consumer<Message>){
        myStompService.subscribe(receiveEndPointPrefix + roomId, Message::class.java){
            callback.accept(it)
        }
    }

    fun deSubscribeRoom(roomId: Int){
        myStompService.disposeTopic(receiveEndPointPrefix + roomId)
    }

    fun deSubscribeAll(){
        myStompService.disposeTopicAll()
    }

    companion object{
        fun getNewInstance() : MessageApiService{
            return MessageApiService()
        }
    }
}