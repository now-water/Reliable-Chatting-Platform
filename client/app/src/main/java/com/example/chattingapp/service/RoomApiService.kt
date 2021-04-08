package com.example.chattingapp.service

import com.example.chattingapp.service.util.rest.RestApiService
import com.example.chattingapp.service.util.rest.RestApiServiceCallback
import io.reactivex.functions.Consumer

class RoomApiService(private val restApiService: RestApiService) {

    fun createRoom(roomName: String, callback : Consumer<String>){
        restApiService.createRoom(roomName).enqueue(RestApiServiceCallback(callback))
    }

    companion object{
        val instance = RoomApiService(RestApiService.instance)
    }
}