package com.example.chattingapp.service

import com.example.chattingapp.dto.ChatRoom
import com.example.chattingapp.dto.EventInvite
import com.example.chattingapp.service.util.rest.RestApiService
import com.example.chattingapp.service.util.rest.RestApiServiceCallback
import com.example.chattingapp.service.util.stomp.MyStompClient
import com.google.gson.Gson
import io.reactivex.functions.Consumer

class RoomApiService(private val restApiService: RestApiService, private val myStompService: MyStompClient) {

    fun getRooms(callback : Consumer<List<ChatRoom>>){
        restApiService.getRooms().enqueue(RestApiServiceCallback(callback))
    }

    fun getRoom(roomId: Int, callback : Consumer<ChatRoom>){
        restApiService.getRoom(roomId).enqueue(RestApiServiceCallback(callback))
    }

    fun createRoom(roomName: String, callback : Consumer<Int>){
        restApiService.createRoom(roomName).enqueue(RestApiServiceCallback(callback))
    }

    fun inviteRoom(roomId: Int, told: Int, callback : Consumer<String>){
        restApiService.inviteRoom(roomId, told).enqueue(RestApiServiceCallback(callback))
    }

    fun sendGreetingEvent(myId:Int, toId:Int, eventInvite: EventInvite){
        myStompService.send("/pub/event/sub/$myId/$toId", Gson().toJson(eventInvite)){

        }
    }

    fun outRoom(roomId: Int, callback : Consumer<String>){
        restApiService.outRoom(roomId).enqueue(RestApiServiceCallback(callback))
    }

    companion object{
        val instance = RoomApiService(RestApiService.instance, MyStompClient.instance)
    }
}